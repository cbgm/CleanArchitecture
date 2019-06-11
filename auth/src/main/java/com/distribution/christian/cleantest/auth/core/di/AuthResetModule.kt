package com.distribution.christian.cleantest.auth.core.di

import com.distribution.christian.cleantest.auth.presentation.reset.ResetPresenter
import com.distribution.christian.cleantest.core.core.di.DiScope
import org.koin.dsl.module.module


val authResetModule = module {
   scope(DiScope.AUTH_RESET.identifier) { ResetPresenter() }
}