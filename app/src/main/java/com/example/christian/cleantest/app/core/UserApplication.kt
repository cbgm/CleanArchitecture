package com.example.christian.cleantest.app.core

import android.app.Application
import com.example.christian.cleantest.core.koin.appModule
import com.example.christian.cleantest.cart.core.di.cartModule
import com.example.christian.cleantest.core.koin.networkModule
import com.example.christian.cleantest.cart.core.di.overviewModule
import org.koin.android.ext.android.startKoin

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, networkModule,
                               overviewModule,
                               cartModule
        ))
    }
}