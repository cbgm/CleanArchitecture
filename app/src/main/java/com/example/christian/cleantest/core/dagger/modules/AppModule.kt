package com.example.christian.cleantest.core.dagger.modules

import android.content.Context
import com.example.christian.cleantest.core.mock.FakeInterceptor
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.data.repository.remote.cart.CartApi
import com.example.christian.cleantest.data.repository.remote.user.UserApi
import com.example.christian.cleantest.presentation.UserApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient


@Module
class AppModule(val app: UserApplication) {

    @Singleton
    @Provides
    fun provideApp() = app

    @Provides
    fun provideContext(): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideItemApi(retrofit: Retrofit): CartApi {
        return retrofit.create(CartApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
                .addInterceptor(FakeInterceptor())
                .build()
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://test-test.domainname.com")
                .client(client)
                .build()
    }

    @Singleton
    @Provides
    fun provideImageUtil(context: Context): ImageUtil {
        return ImageUtil(context)
    }
}