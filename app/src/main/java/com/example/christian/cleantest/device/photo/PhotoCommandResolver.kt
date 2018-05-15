package com.example.christian.cleantest.device.photo

import android.content.Context
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.command.CameraCommand
import com.example.christian.cleantest.device.photo.command.CroppingCommand
import com.example.christian.cleantest.device.photo.command.DeleteCommand
import com.example.christian.cleantest.device.photo.command.GalleryCommand

class PhotoCommandResolver(val applicationContext: Context, private val imageUtil: ImageUtil) {
    lateinit var photoManagerCallback: PhotoManager.PhotoManagerCallback

    fun executeCommand(resultCode: Int, opt: Any? = null) {
        val command = when (resultCode) {
            PhotoManager.CAMERA_RESULT_CODE -> CameraCommand(applicationContext, imageUtil, photoManagerCallback, this::executeCommand)
            PhotoManager.GALLERY_RESULT_CODE -> GalleryCommand(applicationContext, imageUtil, photoManagerCallback, this::executeCommand)
            PhotoManager.DELETE_RESULT_CODE -> DeleteCommand(applicationContext, imageUtil, photoManagerCallback)
            PhotoManager.CROP_RESULT_CODE -> CroppingCommand(applicationContext, imageUtil, photoManagerCallback, opt.toString())
            else -> null
        }
        command?.execute()
    }

    interface ForwardCommand {
        fun execute()
    }
}