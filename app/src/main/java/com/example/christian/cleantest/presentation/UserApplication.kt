package com.example.christian.cleantest.presentation

import android.app.Application
import com.example.christian.cleantest.core.dagger.Injector

class UserApplication : Application() {

    /*val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }*/

    override fun onCreate() {
        super.onCreate()
        //component.inject(this)
        Injector.init(this).inject(this)
    }

}