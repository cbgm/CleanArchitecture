package com.example.christian.cleantest.device.photo

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.christian.cleantest.R
import android.content.Intent
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.presentation.cropview.CropActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotoManager @Inject constructor(val context: Context) {

    private val compositeSubscription = CompositeDisposable()
    private lateinit var callback: PhotoManagerCallback
    private val pickerItems = ArrayList<PickerItem>()
    lateinit var fileName: String

    companion object {
        const val CAMERA_RESULT_CODE: Int = 1
        const val GALLERY_RESULT_CODE: Int = 2
        const val DELETE_RESULT_CODE: Int = 3
        const val CROP_RESULT_CODE: Int = 4
    }

    fun initPicking(fileName: String) {
        this.fileName = fileName
        initItems()
        val adapter = PickerAdapter(pickerItems)
        val pickerBuilder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.PhotopickerTheme)
                .setSingleChoiceItems(adapter, -1, { dialog, which ->
                    val selected = pickerItems[which].value

                    when (selected) {
                        1 -> forwardToCamera()
                        2 -> forwardToGallery()
                        3 -> deletePicture()
                    }
                    dialog.dismiss()
                })
        createImageOptionDialog(pickerBuilder).show()
    }

    private fun initItems() {
        pickerItems.add(PickerItem("Camera", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_menu_camera, null), CAMERA_RESULT_CODE))
        pickerItems.add(PickerItem("Gallery", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_menu_camera, null), GALLERY_RESULT_CODE))
        if (hasPictureDefined()) {
            pickerItems.add(PickerItem("Delete", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_delete, null), DELETE_RESULT_CODE))
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
                .subscribe { cropImage(it) })
    }

    private fun unsubscribe() {
        compositeSubscription.takeIf { !it.isDisposed }?.apply { dispose() }
    }

    private fun subscribeCamera() {

    }

    private fun unsubscribeCamera() {

    }

    private fun cropImage(o: Any) {
        unsubscribe()
        val tempData = o as PhotoCallbackObject

        o.data?.let {
            when (tempData.resultCode) {
                CAMERA_RESULT_CODE -> {

                }
                GALLERY_RESULT_CODE -> {

                }
            }

            val cropPictureIntent = Intent(context, CropActivity::class.java)
            cropPictureIntent.putExtra("Uri", o.data.data.toString())
            (context as AppCompatActivity).startActivityForResult(cropPictureIntent, CROP_RESULT_CODE)
        }
    }

    private fun forwardToGallery() {
        subscribe()
        val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
        takeGalleryPictureIntent.type = "image/*"
        (context as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, GALLERY_RESULT_CODE)
    }

    private fun forwardToCamera() {
        subscribe()
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(context.packageManager) != null) {
            (context as AppCompatActivity).startActivityForResult(takePictureIntent, CAMERA_RESULT_CODE)
        }
    }

    fun setPhotoCallback(callback: PhotoManagerCallback) {
        this.callback = callback
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

    interface PhotoManagerCallback {

        fun croppedResult(image: Bitmap)
    }

}