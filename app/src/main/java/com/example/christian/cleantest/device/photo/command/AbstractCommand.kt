package com.example.christian.cleantest.device.photo.command

import android.app.Activity
import android.net.Uri
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.photo.PhotoCallbackObject
import com.example.christian.cleantest.device.photo.PhotoCommandResolver
import com.example.christian.cleantest.device.photo.PhotoManager
import com.example.christian.cleantest.device.photo.RxPhotoBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

abstract class AbstractCommand(
        val imageUtil: ImageUtil,
        val photoManagerCallback: PhotoManager.PhotoManagerCallback
) : PhotoCommandResolver.ForwardCommand {

    lateinit var exFunc: (Int, String) -> Unit
    lateinit var context: Activity

    constructor(context: Activity,
                imageUtil: ImageUtil,
                photoManagerCallback: PhotoManager.PhotoManagerCallback, func: (Int, String) -> Unit) : this(imageUtil, photoManagerCallback) {
        this.exFunc = func
        this.context = context
    }

    constructor(context: Activity,
                imageUtil: ImageUtil,
                photoManagerCallback: PhotoManager.PhotoManagerCallback) : this(imageUtil, photoManagerCallback) {
        this.context = context
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
                            imageUtil.deleteTempFileByName()
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
                    Uri.fromFile(imageUtil.getExternalFileByImagePath())
                }
            }
            PhotoManager.GALLERY_RESULT_CODE -> callbackObj.data?.data
            else -> null
        }

        uri?.let {
            exFunc(PhotoManager.CROP_RESULT_CODE, it.toString())
        }
    }
}