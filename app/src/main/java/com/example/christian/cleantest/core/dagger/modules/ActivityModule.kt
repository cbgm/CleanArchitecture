package com.example.christian.cleantest.core.dagger.modules

import android.app.Activity
import com.example.christian.cleantest.core.dagger.annotations.ForActivity
import com.example.christian.cleantest.device.photo.PhotoManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @ForActivity
    @Provides
    fun providePhotoManager() : PhotoManager {
        return PhotoManager(activity).apply {
            if (activity is PhotoManager.PhotoManagerCallback)
                this.photoManagerCallback = activity as PhotoManager.PhotoManagerCallback
        }
    }
}