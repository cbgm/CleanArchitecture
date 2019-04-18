package com.distribution.christian.cleantest.core.core.di

import android.content.Context
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.distribution.christian.cleantest.core.core.util.SharedPreference
import com.distribution.christian.cleantest.core.core.util.network.NetworkReceiverManager
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import com.distribution.christian.cleantest.core.data.repository.AuthenticationRepositoryImpl
import com.distribution.christian.cleantest.core.data.repository.remote.AuthenticationFromNetwork
import com.distribution.christian.cleantest.core.data.repository.remote.SearchApi
import com.distribution.christian.cleantest.core.data.repository.remote.UserApi
import com.distribution.christian.cleantest.core.data.service.NetworkChangeModeServiceImpl
import com.distribution.christian.cleantest.core.data.service.PowerSaveModeServiceImpl
import com.distribution.christian.cleantest.core.device.LocalPersistenceManager
import com.distribution.christian.cleantest.core.device.notification.NotificationFactory
import com.distribution.christian.cleantest.core.domain.repository.AuthenticationRepository
import com.distribution.christian.cleantest.core.domain.usecase.ShowNotificationUseCase
import com.distribution.christian.cleantest.core.domain.usecase.SwitchNetworkModeUseCase
import com.distribution.christian.cleantest.core.domain.usecase.SwitchPowerSaveModeUseCase
import com.google.firebase.auth.FirebaseAuth
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
   single { LocalPersistenceManager(SharedPreference()) }
   single { SwitchPowerSaveModeUseCase(get()) }
   single { SwitchNetworkModeUseCase(get()) }
   single { NetworkChangeModeServiceImpl(get()) }
   single { NetworkReceiverManager() }
   single { PowerSaveModeServiceImpl(get(), get()) }
   single { androidApplication().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }
   single { createWebService<UserApi>(get("retrofit1")) }
   single { createWebService<SearchApi>(get("retrofit1")) }
   single { FirebaseAuth.getInstance() }
   single {
      AuthenticationFromNetwork(
            get()
      )
   }
   single<AuthenticationRepository> {
      AuthenticationRepositoryImpl(
            get()
      )
   }

}