package com.example.christian.cleantest.device.photo

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.christian.cleantest.R
import com.example.christian.cleantest.presentation.personalview.CropActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PhotoManager (private val context: Context) {

    private val compositeSubscription = CompositeDisposable()
    private val pickerItems = ArrayList<PickerItem>()
    private lateinit var fileName: String

    companion object {
        const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE: Int = 0
        const val CAMERA_RESULT_CODE: Int = 1
        const val GALLERY_RESULT_CODE: Int = 2
        const val DELETE_RESULT_CODE: Int = 3
        const val CROP_RESULT_CODE: Int = 4
    }

    fun initPicking(fileName: String) {
        this.fileName = "$fileName.png"
        initItems()
        val adapter = PickerAdapter(pickerItems)
        val pickerBuilder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.PhotopickerTheme)
                .setSingleChoiceItems(adapter, -1, { dialog, which ->
                    val selected = pickerItems[which].value

                    when (selected) {
                        CAMERA_RESULT_CODE -> forwardToCamera()
                        GALLERY_RESULT_CODE -> forwardToGallery()
                        DELETE_RESULT_CODE -> deletePicture()
                    }
                    dialog.dismiss()
                })
        createImageOptionDialog(pickerBuilder).show()
    }

    private fun initItems() {
        pickerItems.clear()
        pickerItems.add(PickerItem("Foto aufnehmen", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_photo_camera_black_24dp, null), CAMERA_RESULT_CODE))
        pickerItems.add(PickerItem("Foto hochladen", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_photo_library_black_24dp, null), GALLERY_RESULT_CODE))

        if (hasPictureDefined()) {
            pickerItems.add(PickerItem("Foto löschen", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_delete_black_24dp, null), DELETE_RESULT_CODE))
        }
    }

    private fun hasPictureDefined(): Boolean {
        return SharedPreferencesUtil.get(fileName, context) != null
    }

    private fun createImageOptionDialog(pickerBuilder: AlertDialog.Builder): AlertDialog {
        val dialog = pickerBuilder.create()
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window.setGravity(Gravity.BOTTOM)
        return dialog
    }

    private fun deletePicture() {
        ImageUtil.deleteImageFromInternalStorage(context, fileName)
        SharedPreferencesUtil.delete(fileName, context)
    }

    data class PickerItem(val desc: String, val drawable: Drawable?, val value: Int)

    private fun subscribe() {
        compositeSubscription.add(RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val callbackObj = it as PhotoCallbackObject
                    when(callbackObj.resultCode) {
                        CROP_RESULT_CODE -> savePhoto(it)
                        else -> cropImage(it)
                    }
                })
    }

    private fun unsubscribe() {
        compositeSubscription.takeIf { !it.isDisposed }?.apply { dispose() }
    }

    private fun cropImage(callbackObj: PhotoCallbackObject) {

        val uri: Uri? = when (callbackObj.resultCode) {
            CAMERA_RESULT_CODE -> {
                val bitmap = callbackObj.data?.getParcelableExtra<Bitmap>("data")
                bitmap?.let {
                    ImageUtil.saveBitmapAsImage(context, it, fileName)
                    ImageUtil.getImagePathByName(fileName, context)
                }
            }
            GALLERY_RESULT_CODE -> callbackObj.data?.data
            else -> null
        }

        uri?.let {
            forwardToCropping(it.toString())
        }
    }

    private fun forwardToCropping(uriAsString: String) {
        val cropPictureIntent = Intent(context, CropActivity::class.java)
        cropPictureIntent.putExtra("Uri", uriAsString)
        (context as AppCompatActivity).startActivityForResult(cropPictureIntent, CROP_RESULT_CODE)
    }

    private fun forwardToGallery() {
        subscribe()
        val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
        takeGalleryPictureIntent.type = "image/*"
        (context as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, GALLERY_RESULT_CODE)
    }

    private fun forwardToCamera() {
        subscribe()
        //TODO Refactore method
        getWriteExternalStoragePermission()
    }

    fun loadPhoto(): Bitmap?{
        return null
    }

    private fun savePhoto(callbackObj: PhotoCallbackObject){
        val bitmap = callbackObj.data?.getParcelableExtra<Bitmap>("Image")
        ImageUtil.saveBitmapAsImage(context, bitmap, fileName)
        SharedPreferencesUtil.set(fileName, context)
    }

    private fun hasWriteExternalStoragePermission() = Build.VERSION.SDK_INT < 23 ||
            context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun getWriteExternalStoragePermission() {

        if (Build.VERSION.SDK_INT > 22) {
            (context as AppCompatActivity).requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
        }
    }

    inner class PickerAdapter(val data: ArrayList<PickerItem>) : BaseAdapter() {

        private val inflater: LayoutInflater by lazy {
            LayoutInflater.from(context)
        }

        override fun getItem(position: Int) = data[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = data.size

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val holder: ItemHolder
            val view: View?

            if (convertView == null) {
                view = inflater.inflate(R.layout.photopicker_item, parent, false)
                holder = ItemHolder(view)
                view.tag = holder
            } else {
                view = convertView
                holder = convertView.tag as ItemHolder
            }
            holder.bind(data[position])

            return view
        }

        private inner class ItemHolder(view: View?) {

            val icon: ImageView? = view?.findViewById(R.id.icon)
            val desc: TextView? = view?.findViewById(R.id.desc)

            fun bind(pickerItem: PickerItem) {
                icon?.setImageDrawable(pickerItem.drawable)
                desc?.text = pickerItem.desc
            }
        }
    }

}