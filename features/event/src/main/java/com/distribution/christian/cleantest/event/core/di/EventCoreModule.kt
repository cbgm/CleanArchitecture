package com.distribution.christian.cleantest.event.core.di

import androidx.room.Room
import com.distribution.christian.cleantest.core.core.di.createWebService
import com.distribution.christian.cleantest.event.core.navigation.EventFlowCoordinatorImpl
import com.distribution.christian.cleantest.event.data.cache.EventCache
import com.distribution.christian.cleantest.event.data.cache.EventDatabase
import com.distribution.christian.cleantest.event.data.repository.EventRepositoryImpl
import com.distribution.christian.cleantest.event.data.repository.local.EventDaoFactory
import com.distribution.christian.cleantest.event.data.repository.local.EventFromLocal
import com.distribution.christian.cleantest.event.data.repository.remote.event.EventApi
import com.distribution.christian.cleantest.event.data.repository.remote.event.EventFromNetwork
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.event.domain.usecase.GetEventById
import com.distribution.christian.cleantest.event.domain.usecase.UpdateEvent
import org.koin.core.qualifier.named
import org.koin.dsl.module


val eventCoreModule = module {
   single { EventFlowCoordinatorImpl() }
   single { createWebService<EventApi>(get(named("retrofit1"))) }
   single { EventFromNetwork(get()) }
   single { EventCache() }
   single {
      Room.databaseBuilder(get(), EventDatabase::class.java, "event.db")
            .allowMainThreadQueries()
            .build()
   }
   single { EventFromLocal(EventDaoFactory.SQL) }
   single<EventRepository> {
      EventRepositoryImpl(
            get(),
            get(),
            get(),
            get()
      )
   }
   single { UpdateEvent(get()) }
   single { GetEventById(get()) }
}