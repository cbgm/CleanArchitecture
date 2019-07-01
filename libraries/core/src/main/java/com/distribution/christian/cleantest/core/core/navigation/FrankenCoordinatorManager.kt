package com.distribution.christian.cleantest.core.core.navigation

class FrankenCoordinatorManager: CoordinatorManager() {
   enum class States: State {
      EVENTS,
      SHOP,
      PROFILE,
      AUTH
   }
}