package com.example.christian.cleantest.device.photo.command

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.core.util.PermissionHelper
import com.example.christian.cleantest.device.photo.PhotoManager
import java.io.File

class CameraCommand(
        applicationContext: Context,
        imageUtil: ImageUtil,
        photoManagerCallback: PhotoManager.PhotoManagerCallback,
        func: (Int, String) -> Unit
) : AbstractCommand(applicationContext, imageUtil, photoManagerCallback, func) {

    override fun execute() {
        subscribe()

        if (PermissionHelper.hasWriteExternalStoragePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, applicationContext)) {
            createExternalTempFile()?.let {
                imageUtil.tempFileName = it.name
                val uriForFile = FileProvider.getUriForFile(applicationContext, "com.example.christian.cleantest", it)
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile)
                startCameraActivity(takePictureIntent)
            }
        } else {
            PermissionHelper.getPermissionFromUser(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    PhotoManager.WRITE_EXTERNAL_STORAGE_REQUEST_CODE, applicationContext)
        }
    }

    private fun startCameraActivity(takePictureIntent: Intent) {

        if (takePictureIntent.resolveActivity(applicationContext.packageManager) != null) {
            (applicationContext as AppCompatActivity).startActivityForResult(takePictureIntent, PhotoManager.CAMERA_RESULT_CODE)
        }
    }

    private fun createExternalTempFile(): File? {
        val storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageUtil.fileName, ".jpg", storageDir)
    }
}