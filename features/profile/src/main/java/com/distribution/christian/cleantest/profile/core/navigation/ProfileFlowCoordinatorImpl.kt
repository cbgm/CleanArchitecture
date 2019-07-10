package com.distribution.christian.cleantest.profile.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.coordinator.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewFragment


class ProfileFlowCoordinatorImpl : BaseCoordinatorImpl(), ProfileFlowCoordinator {

   override fun showOverview() {
      this.currentChildFragment = OverviewFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            OverviewFragment.TAG
      )
   }

   override fun navigateDeepLink() {
      //not needed
   }

   override fun navigateLink() {
      showOverview()
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?) {

   }
}