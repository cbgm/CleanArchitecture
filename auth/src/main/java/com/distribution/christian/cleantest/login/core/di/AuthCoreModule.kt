package com.distribution.christian.cleantest.login.core.di

import com.distribution.christian.cleantest.login.core.navigation.AuthFlowCoordinatorImpl
import org.koin.dsl.module.module


val authCoreModule = module {
   single { AuthFlowCoordinatorImpl() }
}