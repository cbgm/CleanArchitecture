package com.example.christian.cleantest.app.core.di

import android.content.Context
import com.example.christian.cleantest.app.core.navigation.RootFlowCoordinatorImpl
import com.example.christian.cleantest.app.core.power.PowerSaveModeReceiver
import com.example.christian.cleantest.app.core.power.PowerSaveModeService
import com.example.christian.cleantest.app.domain.SwitchPowerSaveModeUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appCoreModule = module {
   single { RootFlowCoordinatorImpl() }
   single { SwitchPowerSaveModeUseCase(get()) }
   single { PowerSaveModeService(get(), get()) }
   single { PowerSaveModeReceiver(get(), get()) }
   single { androidApplication().getSharedPreferences("TEST", Context.MODE_PRIVATE) }
}