package com.example.christian.cleantest.core.core.di

import com.example.christian.cleantest.core.core.mock.FakeInterceptor
import com.example.christian.cleantest.core.device.NetManager
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
   single { NetManager(get()) }

   single<Retrofit>(name = "retrofit1") {
      val client = OkHttpClient.Builder()
            .addInterceptor(FakeInterceptor())
            .build()
      Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://test-test.domainname.com")
            .client(client)
            .build()
   }

   single<Retrofit>(name = "retrofit2") {
      val client = OkHttpClient.Builder()
            .addInterceptor(FakeInterceptor())
            .build()
      Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://test-test.bla.com")
            .client(client)
            .build()
   }
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
   return retrofit.create(T::class.java)
}