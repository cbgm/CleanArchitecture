package com.example.christian.cleantest.device.photo.command

import android.content.Context
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.PhotoManager


class DeleteCommand(
        imageUtil: ImageUtil,
        photoManagerCallback: PhotoManager.PhotoManagerCallback
) : AbstractCommand(imageUtil, photoManagerCallback) {

    override fun execute() {
        imageUtil.deleteImageFromInternalStorage()
        photoManagerCallback.imageReady()
    }

}