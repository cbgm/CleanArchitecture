package com.example.christian.cleantest.app.core.di

import com.example.christian.cleantest.app.core.navigation.RootFlowCoordinatorImpl
import com.example.christian.cleantest.cart.core.navigation.CartFlowCoordinatorImpl
import org.koin.dsl.module.module

val appCoreModule = module {
   single{RootFlowCoordinatorImpl()}
}