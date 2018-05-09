package com.example.christian.cleantest.presentation.personalview

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.christian.cleantest.R
import kotlinx.android.synthetic.main.activity_crop.*
import android.app.Activity
import android.content.Intent

class CropActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)

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
            val croppedImage = cropImageView.croppedImage
            val resultIntent = Intent()
            resultIntent.putExtra("Image", croppedImage)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        cancel_btn.setOnClickListener {finish()
        }
        rotate_btn.setOnClickListener {
            cropImageView.rotateImage(90)
        }

    }
}
