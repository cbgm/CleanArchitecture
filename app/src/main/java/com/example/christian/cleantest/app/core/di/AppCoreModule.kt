package com.example.christian.cleantest.app.core.di

import com.example.christian.cleantest.app.core.navigation.RootFlowCoordinatorImpl
import org.koin.dsl.module.module

val appCoreModule = module {
   single { RootFlowCoordinatorImpl() }
}