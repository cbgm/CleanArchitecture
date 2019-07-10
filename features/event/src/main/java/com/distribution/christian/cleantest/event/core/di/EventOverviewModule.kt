package com.distribution.christian.cleantest.event.core.di

import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.data.repository.CityRepositoryImpl
import com.distribution.christian.cleantest.core.data.repository.local.CityFromLocal
import com.distribution.christian.cleantest.core.domain.repository.CityRepository
import com.distribution.christian.cleantest.event.domain.usecase.GetCitysByQuery
import com.distribution.christian.cleantest.event.domain.usecase.GetEventsByCriteria
import com.distribution.christian.cleantest.event.presentation.overview.OverviewPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module


val eventOverviewModule = module {
   scope(named(DiScope.EVENT_OVERVIEW.identifier)) {
      scoped {
         OverviewPresenter(
               get(),
               get(),
               get(),
               get()
         )
      }
   }
   single { GetCitysByQuery(get()) }
   single { GetEventsByCriteria(get()) }
   single<CityRepository> { CityRepositoryImpl(get()) }
   single { CityFromLocal(get()) }
}