package com.example.christian.cleantest.device.photo.command

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.PhotoCallbackObject
import com.example.christian.cleantest.device.photo.PhotoCommandResolver
import com.example.christian.cleantest.device.photo.PhotoManager
import com.example.christian.cleantest.device.photo.RxPhotoBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.io.File

abstract class AbstractCommand(
        val applicationContext: Context,
        val imageUtil: ImageUtil,
        val photoManagerCallback: PhotoManager.PhotoManagerCallback
) : PhotoCommandResolver.ForwardCommand {

    lateinit var exFunc: (Int, String) -> Unit

    constructor(applicationContext: Context,
                imageUtil: ImageUtil,
                photoManagerCallback: PhotoManager.PhotoManagerCallback, func: (Int, String) -> Unit) : this(applicationContext, imageUtil, photoManagerCallback) {
        exFunc = func
    }


    private lateinit var disposable: Disposable

    protected fun subscribe() {
        disposable = RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it as PhotoCallbackObject
                    unsubscribe(disposable)

                    when (it.resultCode) {
                        PhotoManager.CAMERA_RESULT_CODE, PhotoManager.GALLERY_RESULT_CODE -> {
                            cropImage(it)
                        }
                        PhotoManager.CROP_RESULT_CODE -> {
                            photoManagerCallback.imageReady()
                        }
                    }
                }
    }


    private fun unsubscribe(disposable: Disposable) {
        disposable.takeIf { !it.isDisposed }?.apply { dispose() }
    }

    private fun cropImage(callbackObj: PhotoCallbackObject) {

        val uri: Uri? = when (callbackObj.resultCode) {
            PhotoManager.CAMERA_RESULT_CODE -> {
                callbackObj.data?.let {
                    val externalFilesDir: String = getExternalPhotoPath()
                    imageUtil.saveBitmapAsImage(imageUtil.getBitmapFromFile(File(externalFilesDir)))
                    imageUtil.getImagePathByName()
                }
            }
            PhotoManager.GALLERY_RESULT_CODE -> callbackObj.data?.data
            else -> null
        }

        uri?.let {
            exFunc(PhotoManager.CROP_RESULT_CODE, it.toString())
        }
    }


    private fun getExternalPhotoPath() =
            applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).absolutePath + File.separator + imageUtil.tempFileName

}