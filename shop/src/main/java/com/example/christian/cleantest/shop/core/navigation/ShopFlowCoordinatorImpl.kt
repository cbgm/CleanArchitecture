package com.example.christian.cleantest.shop.core.navigation

import com.example.christian.cleantest.shop.presentation.overview.OverviewFragment
import com.example.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.example.christian.cleantest.core.core.util.extension.replaceFragment

class ShopFlowCoordinatorImpl : BaseCoordinatorImpl(), ShopFlowCoordinator {

   override fun showOverview() {
      activity.replaceFragment(
            OverviewFragment.newInstance(),
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
}