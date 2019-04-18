package com.distribution.christian.cleantest.core.device.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.distribution.christian.cleantest.core.domain.single.DefaultSingleObserver
import com.distribution.christian.cleantest.core.domain.usecase.SwitchNetworkModeUseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class NetworkReceiver(
      private val callbackOnChange: Any.() -> Unit
) : BroadcastReceiver(), KoinComponent {

   private val switchNetworkModeUseCase: SwitchNetworkModeUseCase by inject()

   init {
      Timber.v("Network Broadcast registered")
   }

   inner class NetworkChangeModeObserver : DefaultSingleObserver<Boolean>() {

      override fun onSuccess(value: Boolean) {
         if (!value) {
            switchNetworkModeUseCase.dispose()
            callbackOnChange()
         }
      }
   }

   override fun onReceive(context: Context, intent: Intent) {
      switchNetworkModeUseCase.execute(NetworkChangeModeObserver(), Unit)
   }
}