package com.distribution.christian.cleantest.auth.core.di

import com.distribution.christian.cleantest.auth.domain.usecase.GetAuthenticatedUser
import com.distribution.christian.cleantest.auth.domain.usecase.LoginUser
import com.distribution.christian.cleantest.auth.presentation.login.LoginFragment
import com.distribution.christian.cleantest.auth.presentation.login.LoginPresenter
import com.distribution.christian.cleantest.core.core.di.DiScope
import org.koin.core.qualifier.named
import org.koin.dsl.module


val authLoginModule = module {
   scope(named(DiScope.AUTH_LOGIN.identifier)) { scoped { LoginPresenter(get(), get()) } }
   single { LoginUser(get()) }
   single { GetAuthenticatedUser(get()) }
}