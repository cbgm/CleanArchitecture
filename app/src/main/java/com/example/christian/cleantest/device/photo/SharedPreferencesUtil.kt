package com.example.christian.cleantest.device.photo

import android.content.Context

class SharedPreferencesUtil {
    companion object {
        private const val SHAREDPREFERENCE = "Files"

        fun get(id: String, context: Context): String {
            val sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE, Context.MODE_PRIVATE)
            return sharedPreferences.getString(id, null)
        }

        fun set(id: String, context: Context) {
            val sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE, Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(id, id)
        }
    }
}