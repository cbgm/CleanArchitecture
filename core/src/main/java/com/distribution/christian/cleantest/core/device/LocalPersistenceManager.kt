package com.distribution.christian.cleantest.core.device

import com.distribution.christian.cleantest.core.core.util.SharedPreference


class LocalPersistenceManager(private val sharedPreference: SharedPreference) {

   companion object PersistenceKeys {
      private const val NIGHT_MODE = "NIGHT_MODE"
   }

   fun getNightPersistence() = sharedPreference.getValueBoolean(NIGHT_MODE)

   fun setNightPersistence(isNightActive: Boolean) = sharedPreference.save(NIGHT_MODE, isNightActive)
}