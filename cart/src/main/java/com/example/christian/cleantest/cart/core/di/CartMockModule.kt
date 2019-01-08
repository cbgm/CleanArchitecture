package com.example.christian.cleantest.cart.core.di

import com.example.christian.cleantest.cart.domain.mock.CartRepositoryMock
import com.example.christian.cleantest.cart.domain.mock.UserRepositoryMock
import org.koin.dsl.module.module

val cartMockModule = module {
   factory { UserRepositoryMock.instance() }
   factory { CartRepositoryMock.instance() }
}