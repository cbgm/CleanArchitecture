package com.distribution.christian.cleantest.core.core.util

import android.content.SharedPreferences
import org.koin.core.KoinComponent
import org.koin.core.inject


class SharedPreference: KoinComponent {
   private val sharedPreferences: SharedPreferences by inject()

   fun save(key: String, value: String) =
         sharedPreferences.edit().putString(key, value).commit()

   fun save(key: String, value: Int) =
         sharedPreferences.edit().putInt(key, value).commit()

   fun save(key: String, value: Boolean) =
         sharedPreferences.edit().putBoolean(key, value).commit()

   fun getValueString(key: String) = sharedPreferences.getString(key, null)

   fun getValueInt(key: String) = sharedPreferences.getInt(key, 0)

   fun getValueBoolean(key: String) = sharedPreferences.getBoolean(key, false)

   fun clearSharedPreference() = sharedPreferences.edit().clear().apply()

   fun removeValue(key: String) = sharedPreferences.edit().remove(key).apply()
}