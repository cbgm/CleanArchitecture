package com.example.christian.cleantest.device

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class PhotoManager @Inject constructor(val context: Context) {

    companion object {
        //const für callbacks
        val CAMERA_RESULT_CODE: Int = 1
        val GALLERY_RELUT_CODE: Int = 2
    }

    fun initPicking() {

    }

    private fun forwardToGallery() {
        val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
        takeGalleryPictureIntent.type = "image/*"
        (context as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, GALLERY_RELUT_CODE)
    }

    private fun forwardToCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(context.packageManager) != null) {
            (context as AppCompatActivity).startActivityForResult(takePictureIntent, CAMERA_RESULT_CODE)
        }
    }
    //adapter


}