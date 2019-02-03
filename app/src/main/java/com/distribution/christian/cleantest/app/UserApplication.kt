package com.distribution.christian.cleantest.app

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.distribution.christian.cleantest.app.core.di.appCoreModule
import com.distribution.christian.cleantest.event.core.di.eventDetailModule
import com.distribution.christian.cleantest.core.core.di.appModule
import com.distribution.christian.cleantest.event.core.di.eventOverviewModule
import com.distribution.christian.cleantest.core.core.logging.TimberTree
import com.distribution.christian.cleantest.core.core.di.networkModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.PowerManager
import android.support.v7.app.AppCompatDelegate
import com.distribution.christian.cleantest.core.device.power.PowerSaveModeReceiver
import com.distribution.christian.cleantest.event.core.di.eventCoreModule
import com.distribution.christian.cleantest.auth.core.di.authCoreModule
import com.distribution.christian.cleantest.auth.core.di.authRegisterModule
import com.distribution.christian.cleantest.profile.core.di.profileCoreModule
import com.distribution.christian.cleantest.profile.core.di.profileOverviewModule
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.android.inject


class UserApplication : SplitCompatApplication() {

   private val lowPowerReceiver: PowerSaveModeReceiver by lazy {
      PowerSaveModeReceiver {
         restartApplication()
      }
   }

   private val sharedPreferences: SharedPreferences by inject()

   override fun onCreate() {
      super.onCreate()

      initKoin()

      setDayNight()

      registerReceiversAndServices()

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

   private fun initKoin() {
      startKoin(
            this, listOf(
            appModule, networkModule, appCoreModule,
            eventCoreModule, eventOverviewModule, eventDetailModule,
            profileCoreModule, profileOverviewModule, authCoreModule, authRegisterModule
      )
      )
   }

   private fun registerReceiversAndServices() {
      registerPowerSaveModeReceiver()
      //more to come
   }

   private fun registerPowerSaveModeReceiver() {
      val filter = IntentFilter()
      filter.addAction(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED)
      registerReceiver(lowPowerReceiver, filter)
   }

   private fun restartApplication() {
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