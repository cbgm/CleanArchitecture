package com.distribution.christian.cleantest.core.data.service

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.PowerManager
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.service.PowerSaveModeService

class PowerSaveModeServiceImpl(
      private val context: Context,
      private val sharedPreferences: SharedPreferences
): PowerSaveModeService {

   @SuppressLint("ApplySharedPref")
   override fun switchNightDay(): Result<Boolean> {
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
      return Result.Success(currentMode != newMode)
   }
}