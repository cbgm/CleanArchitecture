package com.example.christian.cleantest.device.photo.command

import android.content.Context
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.PhotoManager


class DeleteCommand(
        applicationContext: Context,
        imageUtil: ImageUtil,
        photoManagerCallback: PhotoManager.PhotoManagerCallback
) : AbstractCommand(applicationContext, imageUtil, photoManagerCallback) {

    override fun execute() {
        imageUtil.deleteImageFromInternalStorage()
        photoManagerCallback.imageReady()
    }

}