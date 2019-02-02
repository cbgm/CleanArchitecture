package com.distribution.christian.cleantest.core.data.service

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ResponseCachingInterceptor : Interceptor {

   @Throws(IOException::class)
   override fun intercept(chain: Interceptor.Chain): Response {

      val response = chain.proceed(chain.request())
      return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Access-Control-Allow-Origin")
            .removeHeader("Vary")
            .removeHeader("Cache-Control")
            .header("Cache-Control", "public, max-age=60")
            .build()
   }
}