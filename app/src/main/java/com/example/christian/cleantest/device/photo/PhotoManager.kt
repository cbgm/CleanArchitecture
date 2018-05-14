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

class PhotoManager(private val applicationContext: Context) {

    private val imageUtil by lazy {
        ImageUtil.getInstance(filename, applicationContext)
    }
    private lateinit var galleryDisposable: Disposable
    private lateinit var cameraDisposable: Disposable
    private lateinit var croppingDisposable: Disposable
    private val pickerItems = ArrayList<PhotoOptionItem>()
    internal lateinit var photoManagerCallback: PhotoManagerCallback
    private lateinit var tempFileName: String
    private lateinit var filename: String

    companion object {
        const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE: Int = 0
        const val CAMERA_RESULT_CODE: Int = 1
        const val GALLERY_RESULT_CODE: Int = 2
        const val DELETE_RESULT_CODE: Int = 3
        const val CROP_RESULT_CODE: Int = 4
    }

    fun showPhotoOptions() {
        initPhotoOptions()
        val adapter = PhotoOptionAdapter(pickerItems)
        val pickerBuilder: AlertDialog.Builder = createPhotoOptionBuilder(adapter)
        createPhotoOptionDialog(pickerBuilder).show()
    }

    private fun createPhotoOptionBuilder(adapter: PhotoOptionAdapter): AlertDialog.Builder {
        return AlertDialog.Builder(applicationContext, R.style.PhotopickerTheme)
                .setSingleChoiceItems(adapter, -1, { dialog, which ->
                    val selected = pickerItems[which].value

                    when (selected) {
                        CAMERA_RESULT_CODE -> forwardToCamera()
                        GALLERY_RESULT_CODE -> forwardToGallery()
                        DELETE_RESULT_CODE -> deletePhoto()
                    }
                    dialog.dismiss()
                })
    }

    private fun initPhotoOptions() {
        pickerItems.clear()
        pickerItems.add(PhotoOptionItem("Foto aufnehmen", ResourcesCompat.getDrawable(applicationContext.resources, R.drawable.ic_photo_camera_black_24dp, null), CAMERA_RESULT_CODE))
        pickerItems.add(PhotoOptionItem("Foto hochladen", ResourcesCompat.getDrawable(applicationContext.resources, R.drawable.ic_photo_library_black_24dp, null), GALLERY_RESULT_CODE))

        if (ImageUtil.imageExists()) {
            pickerItems.add(PhotoOptionItem("Foto löschen", ResourcesCompat.getDrawable(applicationContext.resources, R.drawable.ic_delete_black_24dp, null), DELETE_RESULT_CODE))
        }
    }

    private fun createPhotoOptionDialog(pickerBuilder: AlertDialog.Builder): AlertDialog {
        return pickerBuilder.create().apply {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            window.setGravity(Gravity.BOTTOM)
        }
    }

    private fun deletePhoto() {
        ImageUtil.deleteImageFromInternalStorage(applicationContext)
        photoManagerCallback.imageReady()
    }

    data class PhotoOptionItem(val desc: String, val drawable: Drawable?, val value: Int)

    private fun subscribeGallery() {
        galleryDisposable = RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it as PhotoCallbackObject
                    cropImage(it)
                    unsubscribe(galleryDisposable)
                }
    }

    private fun subscribeCamera() {
        cameraDisposable = RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it as PhotoCallbackObject
                    cropImage(it)
                    unsubscribe(cameraDisposable)
                }
    }

    private fun subscribeCropping() {
        croppingDisposable = RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    //it as PhotoCallbackObject
                    //savePhoto(it)
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
                callbackObj.data?.data
                val externalFilesDir: String? = getExternalPhotoPath()
                externalFilesDir?.let {
                    ImageUtil.saveBitmapAsImage(applicationContext, ImageUtil.getBitmapFromFile(File(externalFilesDir)))
                    ImageUtil.getImagePathByName(applicationContext)
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
        //TODO
        subscribeCropping()
        val cropPictureIntent = Intent(applicationContext, CropActivity::class.java)
        cropPictureIntent.putExtra("Uri", uriAsString)
        (applicationContext as AppCompatActivity).startActivityForResult(cropPictureIntent, CROP_RESULT_CODE)
    }

    private fun forwardToGallery() {
        subscribeGallery()
        val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
        takeGalleryPictureIntent.type = "image/*"
        (applicationContext as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, GALLERY_RESULT_CODE)
    }

    internal fun forwardToCamera() {
        subscribeCamera()
        if (hasWriteExternalStoragePermission()) {
            val externalFile = getExternalUri()
            externalFile?.let {
                setTempFileName(it.name)
                val uriForFile = FileProvider.getUriForFile(applicationContext, "com.example.christian.cleantest", externalFile)
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

    private fun getExternalUri(): File? {
        val imageFileName = "bla"
        val storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
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
    }
}