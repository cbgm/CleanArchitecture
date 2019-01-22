package com.distribution.christian.cleantest.profile.core.di

import com.distribution.christian.cleantest.profile.core.navigation.ProfileFlowCoordinatorImpl
import org.koin.dsl.module.module

val profileCoreModule = module {
   single { ProfileFlowCoordinatorImpl() }
}