package com.example.christian.cleantest.device.photo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.File

class ImageUtil() {
    companion object {
        fun saveBitmapAsImage(context: Context, bitmap: Bitmap?, name: String) {
            val outputStream = context.openFileOutput(name, Context.MODE_PRIVATE)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }

        fun loadImage(context: Context, name: String): Bitmap? {
            val openFileInput = context.openFileInput(name)
            return BitmapFactory.decodeStream(openFileInput)
        }

        fun loadImageFromGallery(context: Context, path: String): Bitmap? {
            return MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.Builder().appendPath(path).build())
        }

        fun deleteImageFromInternalStorage(context: Context, name: String) {
            context.deleteFile(name)
        }

        fun getImagePathByName(name: String, context: Context): Uri? {
            val test = Uri.fromFile(File("${context.filesDir.absolutePath}${File.separator}$name"))
            return test
        }
    }
}