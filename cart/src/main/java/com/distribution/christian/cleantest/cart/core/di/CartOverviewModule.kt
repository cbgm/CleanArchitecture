package com.distribution.christian.cleantest.cart.core.di

import com.distribution.christian.cleantest.core.core.di.createWebService
import com.distribution.christian.cleantest.cart.data.repository.UserRepositoryImpl
import com.distribution.christian.cleantest.cart.data.repository.local.UsersFromLocal
import com.distribution.christian.cleantest.cart.data.repository.remote.user.UserApi
import com.distribution.christian.cleantest.cart.data.repository.remote.user.UsersFromNetwork
import com.distribution.christian.cleantest.cart.domain.repository.UserRepository
import com.distribution.christian.cleantest.cart.domain.usecases.GetUsersInPool
import com.distribution.christian.cleantest.cart.presentation.overview.OverviewPresenter
import org.koin.dsl.module.module

val cartOverviewModule = module {
   scope("overview") { OverviewPresenter(get()) }
   single { createWebService<UserApi>(get("retrofit1")) }
   single { UsersFromNetwork(get()) }
   single { UsersFromLocal() }
   single { GetUsersInPool(get()) }
   single<UserRepository> {
      UserRepositoryImpl(
            get(),
            get(),
            get()
      )
   }

}