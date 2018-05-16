package com.example.christian.cleantest.device.photo.command

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.PhotoManager

class GalleryCommand(
        context: Activity,
        imageUtil: ImageUtil,
        photoManagerCallback: PhotoManager.PhotoManagerCallback,
        func: (Int, String) -> Unit
) : AbstractCommand(context, imageUtil, photoManagerCallback, func) {

    override fun execute() {
        subscribe()
        val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
        takeGalleryPictureIntent.type = "image/*"
        (context as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, PhotoManager.GALLERY_RESULT_CODE)
    }

}