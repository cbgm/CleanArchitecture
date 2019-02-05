package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.event.domain.usecase.GetEventsInPool
import com.distribution.christian.cleantest.event.presentation.overview.OverviewPresenter
import org.koin.dsl.module.module


val eventOverviewModule = module {
   scope(DiScope.EVENT_OVERVIEW.identifier) { OverviewPresenter(get(), get(), get()) }
   single { GetEventsInPool(get()) }
}