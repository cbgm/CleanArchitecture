package com.example.christian.cleantest.device.photo

import android.content.Context
import android.content.SharedPreferences
import com.example.christian.cleantest.R

class SharedPreferencesUtil {
    companion object {

        fun get(id: String, context: Context): String? {
            return getSharedPreferences(context).getString(id, null)
        }

        fun set(id: String, context: Context) {
            getSharedPreferences(context).edit().putString(id, id).apply()
        }

        fun delete(id: String, context: Context) {
            getSharedPreferences(context).edit().remove(id).apply()
        }

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(context.resources.getString(R.string.photo_folder), Context.MODE_PRIVATE)
        }
    }
}