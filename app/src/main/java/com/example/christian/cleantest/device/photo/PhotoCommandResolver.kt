package com.example.christian.cleantest.device.photo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.presentation.personalview.CropActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.io.File

class PhotoCommandResolver(val applicationContext: Context, private val imageUtil: ImageUtil) {
    lateinit var disposable: Disposable
    lateinit var photoManagerCallback: PhotoManager.PhotoManagerCallback

    fun executeCommand(command: Int) {
        when(command) {
            PhotoManager.CAMERA_RESULT_CODE -> CameraCommand().execute()
            PhotoManager.GALLERY_RESULT_CODE -> GalleryCommand().execute()
            PhotoManager.DELETE_RESULT_CODE -> DeleteCommand().execute()
        }
    }

    private fun subscribe() {
        disposable = RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it as PhotoCallbackObject
                    unsubscribe(disposable)

                    when (it.resultCode){
                        PhotoManager.CAMERA_RESULT_CODE, PhotoManager.GALLERY_RESULT_CODE  -> {
                            cropImage(it)
                        }
                        PhotoManager.CROP_RESULT_CODE -> {
                            photoManagerCallback.imageReady()
                        }
                    }
                }
    }

    private fun unsubscribe(disposable: Disposable) {
        disposable.takeIf { !it.isDisposed }?.apply { dispose() }
    }


    private fun setTempFileName(name: String) {
        imageUtil.tempFileName = name
    }

    fun setFileName(name: String) {
        imageUtil.fileName= name
    }

    fun setManagerCallback(photoManagerCallback: PhotoManager.PhotoManagerCallback) {
        this.photoManagerCallback = photoManagerCallback
    }

    private fun cropImage(callbackObj: PhotoCallbackObject) {

        val uri: Uri? = when (callbackObj.resultCode) {
            PhotoManager.CAMERA_RESULT_CODE -> {
                callbackObj.data?.let {
                    val externalFilesDir: String? = getExternalPhotoPath()
                    externalFilesDir?.let {
                        imageUtil.saveBitmapAsImage(imageUtil.getBitmapFromFile(File(externalFilesDir)))
                        imageUtil.getImagePathByName()
                    }
                }
            }
            PhotoManager.GALLERY_RESULT_CODE -> callbackObj.data?.data
            else -> null
        }

        uri?.let {
            forwardToCropping(it.toString())
        }
    }

    private fun forwardToCropping(uriAsString: String) {
        subscribe()
        val cropPictureIntent = Intent(applicationContext, CropActivity::class.java)
        cropPictureIntent.putExtra("Uri", uriAsString)
        (applicationContext as AppCompatActivity).startActivityForResult(cropPictureIntent, PhotoManager.CROP_RESULT_CODE)
    }

    private fun getExternalPhotoPath() =
            applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).absolutePath + File.separator + imageUtil.tempFileName

    inner class GalleryCommand: ForwardCommand {
        override fun execute() {
            subscribe()
            val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
            takeGalleryPictureIntent.type = "image/*"
            (applicationContext as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, PhotoManager.GALLERY_RESULT_CODE)
        }

    }

    inner class  CameraCommand: ForwardCommand {
        override fun execute() {
            subscribe()
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
                (applicationContext as AppCompatActivity).startActivityForResult(takePictureIntent, PhotoManager.CAMERA_RESULT_CODE)
            }
        }

        private fun getExternalUri(): File? {
            val storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File.createTempFile(imageUtil.fileName, ".jpg", storageDir)
        }

        private fun hasWriteExternalStoragePermission() = Build.VERSION.SDK_INT < 23 ||
                applicationContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        private fun getWriteExternalStoragePermission() {

            if (Build.VERSION.SDK_INT > 22) {
                (applicationContext as AppCompatActivity).requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PhotoManager.WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
            }
        }

    }

    inner class CroppingCommand : ForwardCommand {
        override fun execute() {

        }

    }

    inner class DeleteCommand: ForwardCommand {
        override fun execute() {
            imageUtil.deleteImageFromInternalStorage()
            photoManagerCallback.imageReady()
        }

    }

    interface ForwardCommand {
        fun execute()
    }
}