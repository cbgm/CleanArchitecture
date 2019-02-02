package com.distribution.christian.cleantest.profile.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewFragment


class ProfileFlowCoordinatorImpl : BaseCoordinatorImpl(), ProfileFlowCoordinator {

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