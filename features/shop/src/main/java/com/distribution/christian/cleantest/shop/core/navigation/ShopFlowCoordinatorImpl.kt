package com.distribution.christian.cleantest.shop.core.navigation

import com.distribution.christian.cleantest.shop.presentation.overview.OverviewFragment
import com.distribution.christian.cleantest.core.core.navigation.coordinator.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.example.christian.cleantest.shop.R

class ShopFlowCoordinatorImpl : BaseCoordinatorImpl(), ShopFlowCoordinator {

   override var replaceableFragmentId = R.id.fragment_container

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