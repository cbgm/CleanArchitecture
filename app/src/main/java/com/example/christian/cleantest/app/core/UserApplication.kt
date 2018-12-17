package com.example.christian.cleantest.app.core

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.PowerManager
import android.support.v7.app.AppCompatDelegate
import com.example.christian.cleantest.app.core.power.PowerSaveModeReceiver
import org.koin.android.ext.android.inject


class UserApplication : Application() {

   private val lowPowerReceiver: PowerSaveModeReceiver by inject()
   private val sharedPreferences: SharedPreferences by inject()

   override fun onCreate() {
      super.onCreate()

      startKoin(
            this, listOf(
            appModule, networkModule, appCoreModule,
            cartCoreModule, cartOverviewModule, cartDetailModule,
            shopCoreModule
      )
      )

      setDayNight()

      registerPowerSaveModeReceiver()

      Timber.plant(TimberTree())
   }

   private fun setDayNight() {
      val nightMode = sharedPreferences.getBoolean("NIGHT_MODE", false)

      if (nightMode) {
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
      } else {
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
      }
   }

   private fun registerPowerSaveModeReceiver() {
      val filter = IntentFilter()
      filter.addAction(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED)
      registerReceiver(lowPowerReceiver, filter)
   }

   fun restartApplication() {
      val startActivity = Intent(this, SplashActivity::class.java)
      val pendingIntentId = 12
      val pendingIntent = PendingIntent.getActivity(
            this,
            pendingIntentId,
            startActivity,
            PendingIntent.FLAG_CANCEL_CURRENT
      )
      val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
      alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent)
      System.exit(0)
   }
}