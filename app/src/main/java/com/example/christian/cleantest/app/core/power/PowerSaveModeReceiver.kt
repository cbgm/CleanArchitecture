package com.example.christian.cleantest.app.core.power

import android.app.Application
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.example.christian.cleantest.app.core.UserApplication
import com.example.christian.cleantest.app.domain.SwitchPowerSaveModeUseCase
import com.example.christian.cleantest.core.domain.single.DefaultSingleObserver
import timber.log.Timber


class PowerSaveModeReceiver(
      private val powerSaveModeUseCase: SwitchPowerSaveModeUseCase,
      private val context: Application
) : BroadcastReceiver() {

   init {
      Timber.v("PowerSave Broadcast registered")
      powerSaveModeUseCase.execute(ShowPowerSaveModeObserver(), Unit)
   }

   inner class ShowPowerSaveModeObserver: DefaultSingleObserver<Boolean>(){

      override fun onSuccess(value: Boolean) {
         if(value){
            (context as UserApplication).restartApplication()
         }
      }
   }

   override fun onReceive(context: Context, intent: Intent) {
      powerSaveModeUseCase.execute(ShowPowerSaveModeObserver(), Unit)
   }
}