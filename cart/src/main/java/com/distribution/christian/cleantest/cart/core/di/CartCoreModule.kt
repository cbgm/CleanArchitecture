package com.distribution.christian.cleantest.cart.core.di

import com.distribution.christian.cleantest.cart.core.navigation.CartFlowCoordinatorImpl
import org.koin.dsl.module.module

val cartCoreModule = module {
   single { CartFlowCoordinatorImpl() }
}