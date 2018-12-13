package com.example.christian.cleantest.core.core.di

import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.example.christian.cleantest.core.device.notification.NotificationFactory
import com.example.christian.cleantest.core.domain.usecases.ShowNotificationUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appModule = module {
   //there is already an injectable application context
   //single { androidApplication() }
   single { ShowNotificationUseCase() }
   factory { NotificationFactory(get()) }
   single { DeepLinkHandler() }
}