package com.distribution.christian.cleantest.cart.core.navigation

import com.distribution.christian.cleantest.cart.presentation.detail.DetailFragment
import com.distribution.christian.cleantest.cart.presentation.overview.OverviewFragment
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment

class CartFlowCoordinatorImpl : BaseCoordinatorImpl(), CartFlowCoordinator {

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
            replaceableFragmentId,
            DetailFragment.TAG
      )
   }

   override fun showOverview() {
      currentFragment = OverviewFragment.newInstance()
      activity.replaceFragment(
            currentFragment,
            replaceableFragmentId,
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