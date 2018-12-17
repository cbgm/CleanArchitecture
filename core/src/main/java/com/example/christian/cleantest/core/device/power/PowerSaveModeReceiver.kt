package com.example.christian.cleantest.core.device.power

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.example.christian.cleantest.core.domain.single.DefaultSingleObserver
import com.example.christian.cleantest.core.domain.usecases.SwitchPowerSaveModeUseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber


class PowerSaveModeReceiver(
      private val callbackOnSuccess: Any.() -> Unit
) : BroadcastReceiver(), KoinComponent {

   private val powerSaveModeUseCase: SwitchPowerSaveModeUseCase by inject()

   init {
      Timber.v("PowerSave Broadcast registered")
      powerSaveModeUseCase.execute(ShowPowerSaveModeObserver(), Unit)
   }

   inner class ShowPowerSaveModeObserver : DefaultSingleObserver<Boolean>() {

      override fun onSuccess(value: Boolean) {
         if (value) {
            callbackOnSuccess()
         }
      }
   }

   override fun onReceive(context: Context, intent: Intent) {
      powerSaveModeUseCase.execute(ShowPowerSaveModeObserver(), Unit)
   }
}