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
import android.net.ConnectivityManager
import android.os.PowerManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.distribution.christian.cleantest.core.device.power.PowerSaveModeReceiver
import com.distribution.christian.cleantest.event.core.di.eventCoreModule
import com.distribution.christian.cleantest.auth.core.di.authCoreModule
import com.distribution.christian.cleantest.auth.core.di.authLoginModule
import com.distribution.christian.cleantest.auth.core.di.authRegisterModule
import com.distribution.christian.cleantest.core.core.util.network.NetworkReceiverManager
import com.distribution.christian.cleantest.core.device.LocalPersistenceManager
import com.distribution.christian.cleantest.core.device.network.NetworkReceiver
import com.distribution.christian.cleantest.event.core.di.eventStarsModule
import com.distribution.christian.cleantest.profile.core.di.profileCoreModule
import com.distribution.christian.cleantest.profile.core.di.profileOverviewModule
import com.distribution.christian.cleantest.social.core.di.socialCoreModule
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.android.inject


class UserApplication : SplitCompatApplication(), LifecycleObserver {

   private val lowPowerReceiver: PowerSaveModeReceiver by lazy {
      PowerSaveModeReceiver {
         restartApplication()
      }
   }

   private val networkReceiver: NetworkReceiver by lazy {
      NetworkReceiver(callbackOnAvailable = { networkAvailable() },
                      callbackOnUnavailable = { networkUnavailable() })
   }

   private val networkReceiverManager: NetworkReceiverManager by inject()

   private val localPersistenceManager: LocalPersistenceManager by inject()

   override fun onCreate() {
      super.onCreate()
      ProcessLifecycleOwner.get().lifecycle.addObserver(this)

      initKoin()

      setDayNight()

      Timber.plant(TimberTree())
   }

   @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
   private fun onAppBackgrounded() {
      unregisterReceiversAndServices()
   }

   @OnLifecycleEvent(Lifecycle.Event.ON_START)
   private fun onAppForegrounded() {
      registerReceiversAndServices()
   }

   private fun setDayNight() {
      val nightMode = localPersistenceManager.getNightPersistence()

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
            eventCoreModule, eventOverviewModule, eventDetailModule, eventStarsModule,
            profileCoreModule, profileOverviewModule,
            authCoreModule, authRegisterModule, authLoginModule,
            socialCoreModule
      )
      )
   }

   private fun registerReceiversAndServices() {
      registerPowerSaveModeReceiver()
      registerNetworkChangeReceiver()
   }

   private fun unregisterReceiversAndServices() {
      unregisterReceiver(networkReceiver)
      unregisterReceiver(lowPowerReceiver)
   }

   private fun registerPowerSaveModeReceiver() {
      val filter = IntentFilter()
      filter.addAction(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED)
      registerReceiver(lowPowerReceiver, filter)
   }

   private fun registerNetworkChangeReceiver() {
      val filter = IntentFilter()
      filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
      registerReceiver(networkReceiver, filter)
   }

   private fun networkAvailable() {
      networkReceiverManager.notifyReceiversAvailable()
   }

   private fun networkUnavailable() {
      networkReceiverManager.notifyReceiversUnavailable()
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