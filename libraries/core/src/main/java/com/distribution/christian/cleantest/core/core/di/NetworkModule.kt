package com.distribution.christian.cleantest.core.core.di

import android.content.Context
import com.distribution.christian.cleantest.core.core.mock.FakeInterceptor
import com.distribution.christian.cleantest.core.data.service.OfflineResponseCacheInterceptor
import com.distribution.christian.cleantest.core.data.service.ResponseCachingInterceptor
import com.distribution.christian.cleantest.core.device.NetManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
   single { NetManager(get()) }

   single<Retrofit>(name = "retrofit1") {

      Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://test-test.domainname.com")
            .client(createOkHttpClient(get()))
            .build()
   }

   single<Retrofit>(name = "retrofit2") {
      Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://test-test.bla.com")
            .client(createOkHttpClient(get()))
            .build()
   }
}


private fun createOkHttpClient(context: Context): OkHttpClient {
   val cacheSize = (24 * 1024 * 1024).toLong()
   val cache = Cache(context.cacheDir, cacheSize)
   return OkHttpClient.Builder()
         .cache(cache)
         .addNetworkInterceptor(ResponseCachingInterceptor())
         .addInterceptor(OfflineResponseCacheInterceptor())
         .addInterceptor(FakeInterceptor())
         .build()
}


inline fun <reified T> createWebService(retrofit: Retrofit): T {
   return retrofit.create(T::class.java)
}