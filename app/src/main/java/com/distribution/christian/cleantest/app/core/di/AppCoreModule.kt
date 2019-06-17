package com.distribution.christian.cleantest.app.core.di

import com.distribution.christian.cleantest.app.core.navigation.MainCoordinatorImpl
import com.distribution.christian.cleantest.app.core.navigation.RootFlowCoordinatorImpl
import org.koin.dsl.module.module


val appCoreModule = module {
   single { RootFlowCoordinatorImpl() }
   single { MainCoordinatorImpl() }
}