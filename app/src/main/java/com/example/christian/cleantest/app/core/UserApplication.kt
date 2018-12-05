package com.example.christian.cleantest.app.core

import android.app.Application
import com.example.christian.cleantest.app.core.di.appCoreModule
import com.example.christian.cleantest.cart.core.di.cartCoreModule
import com.example.christian.cleantest.cart.core.di.cartDetailModule
import com.example.christian.cleantest.core.core.di.appModule
import com.example.christian.cleantest.cart.core.di.cartOverviewModule
import com.example.christian.cleantest.core.core.logging.TimberTree
import com.example.christian.cleantest.core.core.di.networkModule
import com.example.christian.cleantest.shop.core.di.shopCoreModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class UserApplication : Application() {

   override fun onCreate() {
      super.onCreate()

      startKoin(
            this, listOf(
            appModule, networkModule,
            appCoreModule,
            cartCoreModule,
            cartOverviewModule,
            cartDetailModule,
            shopCoreModule
      )
      )
      Timber.plant(TimberTree())
   }
}