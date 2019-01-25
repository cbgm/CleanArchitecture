package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.core.core.di.createWebService
import com.distribution.christian.cleantest.event.core.navigation.EventFlowCoordinatorImpl
import com.distribution.christian.cleantest.event.data.repository.EventRepositoryImpl
import com.distribution.christian.cleantest.event.data.repository.local.EventFromLocal
import com.distribution.christian.cleantest.event.data.repository.remote.event.EventApi
import com.distribution.christian.cleantest.event.data.repository.remote.event.EventFromNetwork
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.event.domain.usecases.UpdateEvent
import org.koin.dsl.module.module

val eventCoreModule = module {
   single { EventFlowCoordinatorImpl() }
   single { createWebService<EventApi>(get("retrofit1")) }
   single { EventFromNetwork(get()) }
   single { EventFromLocal() }
   single<EventRepository> {
      EventRepositoryImpl(
            get(),
            get(),
            get()
      )
   }
   single { UpdateEvent(get()) }
}