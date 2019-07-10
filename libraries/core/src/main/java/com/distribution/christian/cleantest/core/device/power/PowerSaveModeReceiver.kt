package com.distribution.christian.cleantest.core.device.power

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.distribution.christian.cleantest.core.domain.single.DefaultSingleObserver
import com.distribution.christian.cleantest.core.domain.usecase.SwitchPowerSaveModeUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber


class PowerSaveModeReceiver(
      private val callbackOnSuccess: Any.() -> Unit
) : BroadcastReceiver(), KoinComponent {

   private val switchPowerSaveModeUseCase: SwitchPowerSaveModeUseCase by inject()

   init {
      Timber.v("PowerSave Broadcast registered")
      switchPowerSaveModeUseCase.execute(ShowPowerSaveModeObserver(), Unit)
   }

   inner class ShowPowerSaveModeObserver : DefaultSingleObserver<Boolean>() {

      override fun onSuccess(value: Boolean) {
         if (value) {
            switchPowerSaveModeUseCase.dispose()
            callbackOnSuccess()
         }
      }
   }

   override fun onReceive(context: Context, intent: Intent) {
      switchPowerSaveModeUseCase.execute(ShowPowerSaveModeObserver(), Unit)
   }
}
