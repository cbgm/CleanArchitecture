package com.example.christian.cleantest.device

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetManager @Inject constructor (private val applicationContext: Context) {


    val isConnected: Boolean
        get() {
            val service = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = service.activeNetworkInfo
            return  info != null && info.isConnected
        }
}