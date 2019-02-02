package com.distribution.christian.cleantest.shop.core.di

import com.distribution.christian.cleantest.shop.core.navigation.ShopFlowCoordinatorImpl
import org.koin.dsl.module.module


val shopCoreModule = module {
   single { ShopFlowCoordinatorImpl() }
}