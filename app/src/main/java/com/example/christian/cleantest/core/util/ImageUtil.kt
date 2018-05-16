package com.example.christian.cleantest.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import javax.inject.Singleton

@Singleton
class ImageUtil(private val applicationContext: Context) {
    lateinit var fileName: String
    lateinit var tempFileName: String

    fun saveBitmapAsImage(bitmap: Bitmap?) {
        val outputStream = applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }

    fun loadImage(): Bitmap? {
        if (isImagePresent()) {
            val openFileInput = applicationContext.openFileInput(fileName)
            return BitmapFactory.decodeStream(openFileInput)
        }
        return null
    }

    fun getBitmapFromFile(file: File): Bitmap? {
        return BitmapFactory.decodeStream(FileInputStream(file))
    }

    fun deleteImageFromInternalStorage() {
        applicationContext.deleteFile(fileName)
    }

    fun getImagePathByName(): Uri? {
        return Uri.fromFile(getFileByImagePath(fileName))
    }

    fun isImagePresent(): Boolean {
        return getFileByImagePath(fileName).exists()
    }

    fun deleteTempFileByName() {
        File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).absolutePath + File.separator + fileName).delete()
    }

    private fun getFileByImagePath(name: String) =
            File("${applicationContext.filesDir?.absolutePath}${File.separator}$name")
}