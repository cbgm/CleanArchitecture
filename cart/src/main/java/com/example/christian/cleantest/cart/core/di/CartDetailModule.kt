package com.example.christian.cleantest.cart.core.di

import com.example.christian.cleantest.core.core.di.createWebService
import com.example.christian.cleantest.cart.data.repository.CartRepositoryImpl
import com.example.christian.cleantest.cart.data.repository.remote.cart.CartApi
import com.example.christian.cleantest.cart.data.repository.remote.cart.CartFromNetwork
import com.example.christian.cleantest.cart.domain.repository.CartRepository
import com.example.christian.cleantest.cart.domain.usecases.GetCartByUser
import com.example.christian.cleantest.cart.presentation.detail.DetailPresenter
import org.koin.dsl.module.module

val cartDetailModule = module {
   scope("detail") { DetailPresenter(get()) }
   single { createWebService<CartApi>(get("retrofit2")) }
   single { CartFromNetwork(get()) }
   single { GetCartByUser(get()) }
   single<CartRepository> {
      CartRepositoryImpl(
            get()
      )
   }
}