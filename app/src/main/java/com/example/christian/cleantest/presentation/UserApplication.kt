package com.example.christian.cleantest.presentation

import android.app.Application
import com.example.christian.cleantest.core.koin.appModule
import com.example.christian.cleantest.core.koin.cartModule
import com.example.christian.cleantest.core.koin.networkModule
import com.example.christian.cleantest.core.koin.overviewModule
import com.example.christian.cleantest.core.koin.repositoryModule
import org.koin.android.ext.android.startKoin

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, repositoryModule, networkModule, overviewModule, cartModule))
    }
}