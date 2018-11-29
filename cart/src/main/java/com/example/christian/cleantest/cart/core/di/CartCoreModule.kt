package com.example.christian.cleantest.cart.core.di

import com.example.christian.cleantest.cart.core.navigation.CartFlowCoordinatorImpl
import com.example.christian.cleantest.core.core.di.createWebService
import com.example.christian.cleantest.cart.data.repository.CartRepositoryImpl
import com.example.christian.cleantest.cart.data.repository.remote.cart.CartApi
import com.example.christian.cleantest.cart.data.repository.remote.cart.CartFromNetwork
import com.example.christian.cleantest.cart.domain.repository.CartRepository
import com.example.christian.cleantest.cart.domain.usecases.GetCartByUser
import com.example.christian.cleantest.cart.presentation.cartview.DetailPresenter
import org.koin.dsl.module.module

val cartCoreModule = module {
   single{CartFlowCoordinatorImpl()}
}