package com.distribution.christian.cleantest.auth.core.di

import com.distribution.christian.cleantest.auth.presentation.reset.ResetPresenter
import com.distribution.christian.cleantest.core.core.di.DiScope
import org.koin.core.qualifier.named
import org.koin.dsl.module


val authResetModule = module {
   scope(named(DiScope.AUTH_RESET.identifier)) { scoped { ResetPresenter() } }
}