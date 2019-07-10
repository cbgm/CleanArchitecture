package com.distribution.christian.cleantest.profile.core.navigation

import androidx.fragment.app.Fragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewFragment


class ProfileFlowCoordinatorImpl : BaseCoordinatorImpl(), ProfileFlowCoordinator {
   override var replaceableFragmentId = R.id.fragment_container

   override fun showOverview() {
      this.currentChildFragment = OverviewFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            OverviewFragment.TAG
      )
   }

   override fun navigateDeepLink(deepLink: DeepLink) {
      //not needed
   }

   override fun navigateLink() {
      showOverview()
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
      return null
   }
}