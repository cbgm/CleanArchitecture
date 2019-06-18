package com.distribution.christian.cleantest.core.data.service

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.PowerManager
import com.distribution.christian.cleantest.core.device.LocalPersistenceManager
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.service.PowerSaveModeService


class PowerSaveModeServiceImpl(
      private val context: Context,
      private val localPersistenceManager: LocalPersistenceManager
): PowerSaveModeService {

   @SuppressLint("ApplySharedPref")
   override fun switchNightDay(): Result<Boolean> {
      val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
      val currentMode = localPersistenceManager.getNightPersistence()
      val newMode = powerManager.isPowerSaveMode

      localPersistenceManager.setNightPersistence(newMode)

      return Result.Success(currentMode != newMode)
   }
}