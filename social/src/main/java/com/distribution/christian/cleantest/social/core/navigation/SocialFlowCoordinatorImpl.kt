package com.distribution.christian.cleantest.social.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.social.presentation.overview.OverviewFragment


class SocialFlowCoordinatorImpl : BaseCoordinatorImpl(), SocialFlowCoordinator {

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