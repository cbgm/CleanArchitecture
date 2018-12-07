package com.example.christian.cleantest.cart.core.navigation

import com.example.christian.cleantest.cart.R
import com.example.christian.cleantest.cart.presentation.detail.DetailFragment
import com.example.christian.cleantest.cart.presentation.overview.OverviewFragment
import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.example.christian.cleantest.core.core.util.extension.replaceFragment

class CartFlowCoordinatorImpl : BaseCoordinator(), CartFlowCoordinator {

   override fun onDeepLinkBack() {
      when (currentFragment) {
         is DetailFragment -> showOverview()
         else -> activity.finish()
      }
   }

   override fun showDetail(userId: String) {
      currentFragment = DetailFragment.newInstance(userId)
      activity.replaceFragment(
            currentFragment,
            R.id.fragment_container,
            DetailFragment.TAG
      )
   }

   override fun showOverview() {
      currentFragment = OverviewFragment.newInstance()
      activity.replaceFragment(
            currentFragment,
            R.id.fragment_container,
            OverviewFragment.TAG
      )
   }

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  DeepLinkHandler.DeepLinkIdentifier.DETAIL -> showDetail(it.parameter!!)
                  else -> {
                     //not needed
                  }
               }
               isDeepLinkActive = true
            }
   }

   override fun navigateLink() {
      showOverview()
   }
}