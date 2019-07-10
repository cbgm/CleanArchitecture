package com.distribution.christian.cleantest.profile.core.di

import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.profile.data.repository.ProfileRepositoryImpl
import com.distribution.christian.cleantest.profile.data.repository.remote.ProfileFromNetwork
import com.distribution.christian.cleantest.profile.domain.repository.ProfileRepository
import com.distribution.christian.cleantest.profile.domain.usecase.GetProfileOfAuthenticatedUser
import com.distribution.christian.cleantest.profile.domain.usecase.LogoutUser
import com.distribution.christian.cleantest.profile.domain.usecase.UpdateProfileOfAuthenticatedUser
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module


val profileOverviewModule = module {
   scope(named(DiScope.PROFILE_OVERVIEW.identifier)) {
      scoped {
         OverviewPresenter(
               get(),
               get(),
               get()
         )
      }
   }
   single { UpdateProfileOfAuthenticatedUser(get()) }
   single { GetProfileOfAuthenticatedUser(get()) }
   single { LogoutUser(get()) }
   single { ProfileFromNetwork(get(), get()) }
   single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
}