package com.example.christian.cleantest.device.photo.command

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.PhotoManager
import com.example.christian.cleantest.presentation.personalview.CropActivity

class CroppingCommand(
        context: Activity,
        imageUtil: ImageUtil,
        photoManagerCallback: PhotoManager.PhotoManagerCallback,
        private val uriAsString: String
) : AbstractCommand(context, imageUtil, photoManagerCallback) {

    override fun execute() {
        subscribe()
        val cropPictureIntent = Intent(context, CropActivity::class.java)
        cropPictureIntent.putExtra("Uri", uriAsString)
        (context as AppCompatActivity).startActivityForResult(cropPictureIntent, PhotoManager.CROP_RESULT_CODE)
    }
}