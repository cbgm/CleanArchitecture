package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.event.presentation.detail.presenter
import org.koin.core.qualifier.named
import org.koin.dsl.module


val eventDetailModule = module {
   scope(named(DiScope.EVENT_DETAIL.identifier)) { scoped { presenter(get(), get()) } }
}