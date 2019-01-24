package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.event.domain.usecases.GetEventById
import com.distribution.christian.cleantest.event.presentation.detail.DetailPresenter
import org.koin.dsl.module.module

val eventDetailModule = module {
   scope("detail") { DetailPresenter(get()) }
   single { GetEventById(get()) }
}