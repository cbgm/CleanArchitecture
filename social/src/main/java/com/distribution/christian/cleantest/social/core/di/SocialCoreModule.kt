package com.distribution.christian.cleantest.social.core.di

import com.distribution.christian.cleantest.social.core.navigation.SocialFlowCoordinatorImpl
import org.koin.dsl.module.module


val socialCoreModule = module {
   single { SocialFlowCoordinatorImpl() }
}