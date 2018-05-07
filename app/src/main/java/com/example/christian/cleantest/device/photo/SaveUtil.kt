package com.example.christian.cleantest.device.photo

import android.content.Context
import android.graphics.Bitmap

class SaveUtil() {
    companion object {
        fun saveBitmapAsImage(context: Context, bitmap: Bitmap, name: String) {
            val outputStream = context.openFileOutput(name, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }
    }
}