package com.example.christian.cleantest.app.core.power

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.PowerManager
import io.reactivex.Single

class PowerSaveModeService(
      private val context: Context,
      private val sharedPreferences: SharedPreferences
) {

   @SuppressLint("ApplySharedPref")
   fun switchNightDay(): Single<Boolean> {
      val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
      val currentMode = sharedPreferences.getBoolean("NIGHT_MODE", false)
      var newMode: Boolean

      sharedPreferences.edit()
            .apply {

               newMode = if (powerManager.isPowerSaveMode) {
                  this.putBoolean("NIGHT_MODE", true)
                  true
               } else {
                  this.putBoolean("NIGHT_MODE", false)
                  false
               }
               this.commit()
            }
      return Single.just(currentMode != newMode)
   }
}