package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.event.domain.usecases.GetEventsInPool
import com.distribution.christian.cleantest.event.domain.usecases.UpdateEvent
import com.distribution.christian.cleantest.event.presentation.overview.OverviewPresenter
import org.koin.dsl.module.module

val eventOverviewModule = module {
   scope("overview") { OverviewPresenter(get(), get()) }
   single { GetEventsInPool(get()) }
}