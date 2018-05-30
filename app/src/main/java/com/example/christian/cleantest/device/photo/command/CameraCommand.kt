package com.example.christian.cleantest.device.photo.command

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.core.util.PermissionHelper
import com.example.christian.cleantest.device.photo.PhotoManager

class CameraCommand(
        context: Activity,
        imageUtil: ImageUtil,
        photoManagerCallback: PhotoManager.PhotoManagerCallback,
        func: (Int, String) -> Unit
) : AbstractCommand(context, imageUtil, photoManagerCallback, func) {

    override fun execute() {

        if (PermissionHelper.hasWriteExternalStoragePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, context)) {
            subscribe()
            imageUtil.getExternalFileByImagePath().let {
                val uriForFile = FileProvider.getUriForFile(context, "com.example.christian.cleantest", it)
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile)
                startCameraActivity(takePictureIntent)
            }
        } else {
            PermissionHelper.getPermissionFromUser(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    PhotoManager.WRITE_EXTERNAL_STORAGE_REQUEST_CODE, context)
        }
    }

    private fun startCameraActivity(takePictureIntent: Intent) {

        if (takePictureIntent.resolveActivity(context.packageManager) != null) {
            (context as AppCompatActivity).startActivityForResult(takePictureIntent, PhotoManager.CAMERA_RESULT_CODE)
        }
    }
}