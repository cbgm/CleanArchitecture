package com.example.christian.cleantest.core.util

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageUtil @Inject constructor(private val applicationContext: Context) {

    companion object {
        private const val LICENSES_DIR_NAME: String = "licenses"
    }

    lateinit var fileName: String
    var path: String? = null

    fun saveBitmapAsImage(bitmap: Bitmap?) {
        if (path != null && hasPermissionToWriteInStorage()) {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(File(path + fileName)))
        }
    }

    fun getValidFileName(i: Int = 1): String {
        fileName = "Seite$i.jpg"
        if (File(path + "Seite$i.jpg").exists()) {
            getValidFileName(i + 1)
        }
        return fileName
    }

    fun loadImage(): Bitmap? {
        return if (path != null && hasPermissionToWriteInStorage() && isImagePresent()) {
            BitmapFactory.decodeStream(FileInputStream(path + fileName))
        } else null
    }

    private fun hasPermissionToWriteInStorage() =
            PermissionHelper.hasWriteExternalStoragePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, applicationContext.applicationContext)

    fun deleteImageFromInternalStorage() {
        File(path + fileName).delete()
    }

    fun isImagePresent(): Boolean {
        return File(path + fileName).exists()
    }

    fun getExternalFileByImagePath() =
            File(path + fileName)

    fun renameLicenseFile(currentName: String, newFileName: String): Boolean {
        return File(path + currentName)
                .renameTo(File(path + newFileName.trim() + ".jpg"))
    }

    fun createLicensesPath() {
        File(path).takeIf { !it.exists() }?.apply { mkdirs() }
    }

    fun licenseFileExists(fileName: String): Boolean {
        return File(path + fileName).exists()
    }

    fun licensesPathExists(): Boolean {
        return File(path).exists()
    }

    fun setLicensePath(carId: String) {
        path = applicationContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).absolutePath +
                File.separator +
                carId +
                File.separator +
                LICENSES_DIR_NAME +
                File.separator
    }

    fun setCarPath(carId: String) {
        path = applicationContext.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES).absolutePath +
                File.separator +
                carId +
                File.separator
    }
}