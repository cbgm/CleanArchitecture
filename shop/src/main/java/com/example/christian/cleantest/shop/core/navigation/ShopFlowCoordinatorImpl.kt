package com.example.christian.cleantest.shop.core.navigation

import com.example.christian.cleantest.shop.R
import com.example.christian.cleantest.shop.presentation.overview.OverviewFragment
import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.util.extension.replaceFragment

class ShopFlowCoordinatorImpl : BaseCoordinator(), ShopFlowCoordinator {

   override fun showOverview() {
      activity.replaceFragment(
            OverviewFragment.newInstance(),
            R.id.fragment_container,
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