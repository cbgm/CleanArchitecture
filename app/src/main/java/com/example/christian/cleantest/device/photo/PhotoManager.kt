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
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
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
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.presentation.personalview.CropActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.io.File

class PhotoManager(private val applicationContext: Context, private val imageUtil: ImageUtil) {

    private lateinit var chooserDisposable: Disposable
    private lateinit var croppingDisposable: Disposable
    private val photoOptionItems = ArrayList<PhotoOptionItem>()
    internal lateinit var photoManagerCallback: PhotoManagerCallback
    private lateinit var tempFileName: String
    private var filename: String? = null

    companion object {
        const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE: Int = 0
        const val CAMERA_RESULT_CODE: Int = 1
        const val GALLERY_RESULT_CODE: Int = 2
        const val DELETE_RESULT_CODE: Int = 3
        const val CROP_RESULT_CODE: Int = 4
    }

    fun showPhotoOptions() {
        initPhotoOptions()
        val adapter = PhotoOptionAdapter(photoOptionItems)
        val pickerBuilder: AlertDialog.Builder = createPhotoOptionBuilder(adapter)
        createPhotoOptionDialog(pickerBuilder).show()
    }

    fun setPhotoManagerCallback(photoManagerCallback: PhotoManagerCallback) {
        this.photoManagerCallback = photoManagerCallback
    }

    private fun createPhotoOptionBuilder(adapter: PhotoOptionAdapter): AlertDialog.Builder {
        return AlertDialog.Builder(applicationContext, R.style.PhotopickerTheme)
                .setSingleChoiceItems(adapter, -1, { dialog, which ->
                    val selected = photoOptionItems[which].value

                    when (selected) {
                        CAMERA_RESULT_CODE -> forwardToCamera()
                        GALLERY_RESULT_CODE -> forwardToGallery()
                        DELETE_RESULT_CODE -> deletePhoto()
                    }
                    dialog.dismiss()
                })
    }

    private fun initPhotoOptions() {
        photoOptionItems.clear()
        photoOptionItems.add(PhotoOptionItem("Foto aufnehmen", ResourcesCompat.getDrawable(applicationContext.resources, R.drawable.ic_photo_camera_black_24dp, null), CAMERA_RESULT_CODE))
        photoOptionItems.add(PhotoOptionItem("Foto hochladen", ResourcesCompat.getDrawable(applicationContext.resources, R.drawable.ic_photo_library_black_24dp, null), GALLERY_RESULT_CODE))

        if (imageUtil.isImagePresent()) {
            photoOptionItems.add(PhotoOptionItem("Foto löschen", ResourcesCompat.getDrawable(applicationContext.resources, R.drawable.ic_delete_black_24dp, null), DELETE_RESULT_CODE))
        }
    }

    private fun createPhotoOptionDialog(pickerBuilder: AlertDialog.Builder): AlertDialog {
        return pickerBuilder.create().apply {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            window.setGravity(Gravity.BOTTOM)
        }
    }

    private fun deletePhoto() {
        imageUtil.deleteImageFromInternalStorage()
        photoManagerCallback.imageReady()
    }

    data class PhotoOptionItem(val desc: String, val drawable: Drawable?, val value: Int)

    private fun subscribeChooser() {
        chooserDisposable = RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it as PhotoCallbackObject
                    cropImage(it)
                    unsubscribe(chooserDisposable)
                }
    }

    private fun subscribeCropping() {
        croppingDisposable = RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    unsubscribe(croppingDisposable)
                    photoManagerCallback.imageReady()
                }
    }

    private fun unsubscribe(disposable: Disposable) {
        disposable.takeIf { !it.isDisposed }?.apply { dispose() }
    }

    private fun cropImage(callbackObj: PhotoCallbackObject) {

        val uri: Uri? = when (callbackObj.resultCode) {
            CAMERA_RESULT_CODE -> {
                callbackObj.data?.let {
                    val externalFilesDir: String? = getExternalPhotoPath()
                    externalFilesDir?.let {
                        imageUtil.saveBitmapAsImage(imageUtil.getBitmapFromFile(File(externalFilesDir)))
                        imageUtil.getImagePathByName()
                    }
                }
            }
            GALLERY_RESULT_CODE -> callbackObj.data?.data
            else -> null
        }

        uri?.let {
            forwardToCropping(it.toString())
        }
    }

    private fun getExternalPhotoPath() =
            applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).absolutePath + File.separator + tempFileName

    private fun forwardToCropping(uriAsString: String) {
        subscribeCropping()
        val cropPictureIntent = Intent(applicationContext, CropActivity::class.java)
        cropPictureIntent.putExtra("Uri", uriAsString)
        (applicationContext as AppCompatActivity).startActivityForResult(cropPictureIntent, CROP_RESULT_CODE)
    }

    private fun forwardToGallery() {
        subscribeChooser()
        val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
        takeGalleryPictureIntent.type = "image/*"
        (applicationContext as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, GALLERY_RESULT_CODE)
    }

    internal fun forwardToCamera() {
        subscribeChooser()
        if (hasWriteExternalStoragePermission()) {
            createExternalTempFile()?.let {
                setTempFileName(it.name)
                val uriForFile = FileProvider.getUriForFile(applicationContext, "com.example.christian.cleantest", it)
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile)
                startCameraActivity(takePictureIntent)
            }
        } else {
            getWriteExternalStoragePermission()
        }
    }

    private fun startCameraActivity(takePictureIntent: Intent) {
        if (takePictureIntent.resolveActivity(applicationContext.packageManager) != null) {
            (applicationContext as AppCompatActivity).startActivityForResult(takePictureIntent, CAMERA_RESULT_CODE)
        }
    }

    private fun hasWriteExternalStoragePermission() = Build.VERSION.SDK_INT < 23 ||
            applicationContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun getWriteExternalStoragePermission() {

        if (Build.VERSION.SDK_INT > 22) {
            (applicationContext as AppCompatActivity).requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
        }
    }

    private fun createExternalTempFile(): File? {
        val storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(filename, ".jpg", storageDir)
    }

    fun loadPhoto() = imageUtil.loadImage()

    fun savePhoto(bitmap: Bitmap) = imageUtil.saveBitmapAsImage(bitmap)

    inner class PhotoOptionAdapter(val data: ArrayList<PhotoOptionItem>) : BaseAdapter() {

        private val inflater: LayoutInflater by lazy {
            LayoutInflater.from(applicationContext)
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

            fun bind(photoOptionItem: PhotoOptionItem) {
                icon?.setImageDrawable(photoOptionItem.drawable)
                desc?.text = photoOptionItem.desc
            }
        }
    }

    interface PhotoManagerCallback {
        fun imageReady()
    }

    private fun setTempFileName(name: String) {
        tempFileName = name
    }

    fun setFileName(name: String) {
        filename = name
        imageUtil.setFileName(filename!!)
    }
}