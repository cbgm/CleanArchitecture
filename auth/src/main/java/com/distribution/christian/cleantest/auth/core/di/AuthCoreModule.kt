package com.distribution.christian.cleantest.auth.core.di

import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl
import com.distribution.christian.cleantest.auth.data.repository.AuthenticationRepositoryImpl
import com.distribution.christian.cleantest.auth.data.repository.remote.auth.AuthenticationFromNetwork
import com.distribution.christian.cleantest.auth.domain.repository.AuthenticationRepository
import org.koin.dsl.module.module


val authCoreModule = module {
   single { AuthFlowCoordinatorImpl() }
   single { AuthenticationFromNetwork(get()) }
   single<AuthenticationRepository> {
      AuthenticationRepositoryImpl(
            get()
      )
   }
}