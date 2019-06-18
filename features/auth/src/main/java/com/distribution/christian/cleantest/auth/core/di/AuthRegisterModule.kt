package com.distribution.christian.cleantest.auth.core.di

import com.distribution.christian.cleantest.auth.domain.usecase.RegisterNewUser
import com.distribution.christian.cleantest.auth.presentation.register.RegisterPresenter
import com.distribution.christian.cleantest.core.core.di.DiScope
import org.koin.dsl.module.module


val authRegisterModule = module {
   scope(DiScope.AUTH_REGISTER.identifier) { RegisterPresenter(get()) }
   single { RegisterNewUser(get()) }
}