package com.example.christian.cleantest.core.core.di

import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appModule = module {
   single { androidApplication() }

   single { DeepLinkHandler() }
}