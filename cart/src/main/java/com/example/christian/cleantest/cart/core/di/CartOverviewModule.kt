package com.example.christian.cleantest.cart.core.di

import com.example.christian.cleantest.core.core.di.createWebService
import com.example.christian.cleantest.cart.data.repository.UserRepositoryImpl
import com.example.christian.cleantest.cart.data.repository.local.UsersFromLocal
import com.example.christian.cleantest.cart.data.repository.remote.user.UserApi
import com.example.christian.cleantest.cart.data.repository.remote.user.UsersFromNetwork
import com.example.christian.cleantest.cart.domain.repository.UserRepository
import com.example.christian.cleantest.cart.domain.usecases.GetUsersInPool
import com.example.christian.cleantest.cart.presentation.overview.OverviewPresenter
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