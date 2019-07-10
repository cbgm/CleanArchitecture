package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.event.domain.usecase.DeleteEventFromCache
import com.distribution.christian.cleantest.event.domain.usecase.GetEventsFromCache
import com.distribution.christian.cleantest.event.presentation.stars.StarsPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module


val eventStarsModule = module {
   scope(named(DiScope.EVENT_STARS.identifier)) { scoped { StarsPresenter(get(), get()) } }
   single { GetEventsFromCache(get()) }
   single { DeleteEventFromCache(get()) }
}
