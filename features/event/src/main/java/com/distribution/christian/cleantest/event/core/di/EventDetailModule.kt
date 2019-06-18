package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.event.domain.usecase.GetEventById
import com.distribution.christian.cleantest.event.presentation.detail.DetailPresenter
import org.koin.dsl.module.module


val eventDetailModule = module {
   scope(DiScope.EVENT_DETAIL.identifier) { DetailPresenter(get(), get()) }
}