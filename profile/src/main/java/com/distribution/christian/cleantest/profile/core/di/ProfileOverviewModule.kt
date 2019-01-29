package com.distribution.christian.cleantest.profile.core.di

import com.distribution.christian.cleantest.profile.presentation.overview.OverviewPresenter
import org.koin.dsl.module.module

val profileOverviewModule = module {
   single { OverviewPresenter() }
}