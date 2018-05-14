package com.example.christian.cleantest.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileInputStream
import java.lang.ref.WeakReference

class ImageUtil {
    lateinit var fileName: String
    var contextRef: WeakReference<Context>? = null

    companion object {
        @Volatile
        private var INSTANCE: ImageUtil? = null

        fun getInstance(filename: String, context: Context): ImageUtil {
            INSTANCE?.let {
                INSTANCE = ImageUtil()
                INSTANCE?.fileName = filename
                INSTANCE?.contextRef = WeakReference(context)
                return INSTANCE as ImageUtil
            }
            return this.INSTANCE!!
        }
    }

    fun saveBitmapAsImage(bitmap: Bitmap?, name: String) {
        val outputStream = contextRef?.get()?.openFileOutput(name, Context.MODE_PRIVATE)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }

    fun loadImage(name: String): Bitmap? {
        val openFileInput = contextRef?.get()?.openFileInput(name)
        return BitmapFactory.decodeStream(openFileInput)
    }

    fun getBitmapFromFile(file: File): Bitmap? {
        return BitmapFactory.decodeStream(FileInputStream(file))
    }

    fun deleteImageFromInternalStorage(name: String) {
        contextRef?.get()?.deleteFile(name)
    }

    fun getImagePathByName(name: String): Uri? {
        return Uri.fromFile(getFileByImagePath())
    }

    fun isImagePresent(): Boolean {
        return getFileByImagePath().exists()
    }

    private fun getFileByImagePath() =
            File("${contextRef?.get()?.filesDir?.absolutePath}${File.separator}$fileName")
}