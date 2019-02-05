package com.distribution.christian.cleantest.auth.core.di

import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module.module


val authCoreModule = module {
   single { AuthFlowCoordinatorImpl() }
   single { FirebaseAuth.getInstance() }
}