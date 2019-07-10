package com.distribution.christian.cleantest.core.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager

class FrankenCoordinatorManager: CoordinatorManager() {

   enum class States: State {
      EVENTS,
      SHOP,
      PROFILE,
      AUTH
   }
}