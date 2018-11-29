package com.example.christian.cleantest.shop.core.di

import com.example.christian.cleantest.shop.core.navigation.ShopFlowCoordinatorImpl
import org.koin.dsl.module.module

val shopCoreModule = module {
   single { ShopFlowCoordinatorImpl() }
}