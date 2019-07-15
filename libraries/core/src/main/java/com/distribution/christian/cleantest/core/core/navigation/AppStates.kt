package com.distribution.christian.cleantest.core.core.navigation

import com.christian.multinavlib.navigation.coordinator.CoordinatorManager


enum class AppStates: CoordinatorManager.State {
   SPLASH,
   AUTH,
   MAIN
}