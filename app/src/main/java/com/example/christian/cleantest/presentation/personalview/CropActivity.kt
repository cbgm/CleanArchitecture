package com.example.christian.cleantest.presentation.personalview

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.dagger.Injector
import com.example.christian.cleantest.device.photo.PhotoManager
import kotlinx.android.synthetic.main.activity_crop.*
import javax.inject.Inject

class CropActivity : AppCompatActivity() {

    @Inject
    lateinit var photoManager: PhotoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)
        Injector.initActivityComponent(this).inject(this)

        try {
            val uri = Uri.parse(intent.getStringExtra("Uri"))
            prepareView(uri)

        } catch (ex: Exception) {
            finish()
        }
    }

    private fun prepareView(uri: Uri) {
        cropImageView.setImageUriAsync(uri)

        crop_btn.setOnClickListener {
            photoManager.savePhoto(cropImageView.croppedImage)
            setResult(Activity.RESULT_OK)
            finish()
        }
        cancel_btn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        rotate_btn.setOnClickListener {
            cropImageView.rotateImage(90)
        }

    }
}
