package com.distribution.christian.cleantest.cart.core.di

import com.distribution.christian.cleantest.core.core.di.createWebService
import com.distribution.christian.cleantest.cart.data.repository.CartRepositoryImpl
import com.distribution.christian.cleantest.cart.data.repository.remote.cart.CartApi
import com.distribution.christian.cleantest.cart.data.repository.remote.cart.CartFromNetwork
import com.distribution.christian.cleantest.cart.domain.repository.CartRepository
import com.distribution.christian.cleantest.cart.domain.usecases.GetCartByUser
import com.distribution.christian.cleantest.cart.presentation.detail.DetailPresenter
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