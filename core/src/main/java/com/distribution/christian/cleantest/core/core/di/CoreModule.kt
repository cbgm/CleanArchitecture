package com.distribution.christian.cleantest.core.core.di

import android.content.Context
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import com.distribution.christian.cleantest.core.data.service.PowerSaveModeServiceImpl
import com.distribution.christian.cleantest.core.device.notification.NotificationFactory
import com.distribution.christian.cleantest.core.domain.usecases.ShowNotificationUseCase
import com.distribution.christian.cleantest.core.domain.usecases.SwitchPowerSaveModeUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

private const val PREF_NAME = "xyzTest"

val appModule = module {
   //there is already an injectable application context
   //single { androidApplication() }
   single { SplitInstallRequester(get()) }
   single { ShowNotificationUseCase() }
   factory { NotificationFactory(get()) }
   single { DeepLinkHandler() }
   single { SwitchPowerSaveModeUseCase(get()) }
   single { PowerSaveModeServiceImpl(get(), get()) }
   single { androidApplication().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }
}