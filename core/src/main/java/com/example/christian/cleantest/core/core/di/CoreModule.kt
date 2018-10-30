package com.example.christian.cleantest.core.koin

import com.example.christian.cleantest.core.core.mock.FakeInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
   single { androidApplication() }

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