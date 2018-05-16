package com.example.christian.cleantest.device.photo

import android.app.Activity
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.command.CameraCommand
import com.example.christian.cleantest.device.photo.command.CroppingCommand
import com.example.christian.cleantest.device.photo.command.DeleteCommand
import com.example.christian.cleantest.device.photo.command.GalleryCommand
import javax.inject.Inject

class PhotoCommandResolver @Inject constructor(
        private val context: Activity,
        private val imageUtil: ImageUtil
) {
    lateinit var photoManagerCallback: PhotoManager.PhotoManagerCallback

    fun executeCommand(resultCode: Int, opt: Any? = null) {
        val command = when (resultCode) {
            PhotoManager.CAMERA_RESULT_CODE -> CameraCommand(context, imageUtil, photoManagerCallback, this::executeCommand)
            PhotoManager.GALLERY_RESULT_CODE -> GalleryCommand(context, imageUtil, photoManagerCallback, this::executeCommand)
            PhotoManager.DELETE_RESULT_CODE -> DeleteCommand(imageUtil, photoManagerCallback)
            PhotoManager.CROP_RESULT_CODE -> CroppingCommand(context, imageUtil, photoManagerCallback, opt.toString())
            else -> null
        }
        command?.execute()
    }

    interface ForwardCommand {
        fun execute()
    }
}