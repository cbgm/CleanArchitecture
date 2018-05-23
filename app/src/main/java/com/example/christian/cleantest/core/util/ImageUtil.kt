package com.example.christian.cleantest.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageUtil @Inject constructor(private val applicationContext: Context) {

    companion object {
        private const val LICENSES_DIR_NAME: String = "licenses"
    }

    lateinit var fileName: String

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
        return Uri.fromFile(getFileByImagePath())
    }

    fun isImagePresent(): Boolean {
        return getFileByImagePath().exists()
    }

    fun deleteTempFileByName() {
        getExternalFileByImagePath().delete()
    }

    fun getExternalFileByImagePath() =
            File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).absolutePath + File.separator + fileName)

    private fun getFileByImagePath() =
            File("${applicationContext.filesDir?.absolutePath}${File.separator}$fileName")

    fun renameLicenseFile(carId: String, newFileName: String): Boolean {
        return File(getLicensesPath(carId) + File.separator + fileName)
                .renameTo(File(getLicensesPath(carId) + File.separator + newFileName + ".jpg"))
    }

    fun createLicensesPath(carId: String) {
        File(applicationContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).absolutePath
                + File.separator
                + carId
                + File.separator
                + LICENSES_DIR_NAME
        ).mkdirs()
    }

    fun licenseFileExists(carId: String, fileName: String): Boolean {
        return File(applicationContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).absolutePath
                + File.separator
                + carId
                + File.separator
                + LICENSES_DIR_NAME
                + fileName
        ).exists()
    }

    fun licensesPathExists(carId: String): Boolean {
        return File(applicationContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).absolutePath
                + File.separator
                + carId
                + File.separator
                + LICENSES_DIR_NAME
        ).exists()
    }

    fun getLicensesPath(carId: String): String {
        return applicationContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).absolutePath +
                File.separator +
                carId +
                File.separator +
                LICENSES_DIR_NAME
    }
}