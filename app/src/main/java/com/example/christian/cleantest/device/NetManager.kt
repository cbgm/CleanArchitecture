package com.example.christian.cleantest.device

import android.content.Context
import android.net.ConnectivityManager

class NetManager constructor (private val applicationContext: Context) {


    val isConnected: Boolean
        get() {
            val service = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = service.activeNetworkInfo
            return  info != null && info.isConnected
        }
}