package com.distribution.christian.cleantest.core.data.service

import com.distribution.christian.cleantest.core.device.NetManager
import okhttp3.Interceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException


class OfflineResponseCacheInterceptor : Interceptor, KoinComponent {
   private val netManager: NetManager by inject()

   @Throws(IOException::class)
   override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
      var request = chain.request()
      if (!netManager.isConnected) {
         request = request.newBuilder()
               .removeHeader("Pragma")
               .removeHeader("Access-Control-Allow-Origin")
               .removeHeader("Vary")
               .removeHeader("Cache-Control")
               .header(
                     "Cache-Control",
                     "public, only-if-cached, max-stale= 60"
               )
               .build()
      }
      return chain.proceed(request)
   }
}