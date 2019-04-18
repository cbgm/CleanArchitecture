package com.distribution.christian.cleantest.core.data.service

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.service.NetworkChangeModeService


class NetworkChangeModeServiceImpl(
      private val context: Context
) : NetworkChangeModeService {

   @SuppressLint("ApplySharedPref")
   override fun isNetworkAvailable(): Result<Boolean> {
      val service = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val info = service.activeNetworkInfo
      return Result.Success(info != null && info.isConnected)
   }
}