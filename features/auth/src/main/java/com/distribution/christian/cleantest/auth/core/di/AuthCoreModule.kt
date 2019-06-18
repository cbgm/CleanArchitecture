package com.distribution.christian.cleantest.auth.core.di

import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl
import org.koin.dsl.module.module


val authCoreModule = module {
   single { AuthFlowCoordinatorImpl() }
}