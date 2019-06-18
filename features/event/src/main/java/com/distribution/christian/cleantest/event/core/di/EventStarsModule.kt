package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.event.domain.usecase.DeleteEventFromCache
import com.distribution.christian.cleantest.event.domain.usecase.GetEventById
import com.distribution.christian.cleantest.event.domain.usecase.GetEventsFromCache
import com.distribution.christian.cleantest.event.presentation.detail.DetailPresenter
import com.distribution.christian.cleantest.event.presentation.stars.StarsPresenter
import org.koin.dsl.module.module


val eventStarsModule = module {
   scope(DiScope.EVENT_STARS.identifier) { StarsPresenter(get(), get()) }
   single { GetEventsFromCache(get()) }
   single { DeleteEventFromCache(get()) }
}
