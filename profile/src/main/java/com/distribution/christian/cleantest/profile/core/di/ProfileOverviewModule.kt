package com.distribution.christian.cleantest.profile.core.di

import com.distribution.christian.cleantest.core.data.repository.remote.ProfileFromNetwork
import com.distribution.christian.cleantest.profile.data.repository.ProfileRepositoryImpl
import com.distribution.christian.cleantest.profile.domain.repository.ProfileRepository
import com.distribution.christian.cleantest.profile.domain.usecase.GetProfileOfAuthenticatedUser
import com.distribution.christian.cleantest.profile.domain.usecase.UpdateProfileOfAuthenticatedUser
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewPresenter
import org.koin.dsl.module.module

val profileOverviewModule = module {
   single { OverviewPresenter(get(), get()) }
   single { UpdateProfileOfAuthenticatedUser(get()) }
   single { GetProfileOfAuthenticatedUser(get()) }
   single { ProfileFromNetwork(get(), get()) }
   single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
}