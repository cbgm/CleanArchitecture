package com.example.christian.cleantest.device.photo.command

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.PhotoManager
import com.example.christian.cleantest.presentation.personalview.CropActivity

class CroppingCommand(
        applicationContext: Context,
        imageUtil: ImageUtil,
        photoManagerCallback: PhotoManager.PhotoManagerCallback,
        private val uriAsString: String
) : AbstractCommand(applicationContext, imageUtil, photoManagerCallback) {

    override fun execute() {
        subscribe()
        val cropPictureIntent = Intent(applicationContext, CropActivity::class.java)
        cropPictureIntent.putExtra("Uri", uriAsString)
        (applicationContext as AppCompatActivity).startActivityForResult(cropPictureIntent, PhotoManager.CROP_RESULT_CODE)
    }
}