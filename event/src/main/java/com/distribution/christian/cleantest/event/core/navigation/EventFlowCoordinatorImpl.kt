package com.distribution.christian.cleantest.event.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragmentwithSharedElement
import com.distribution.christian.cleantest.event.presentation.detail.DetailFragment
import com.distribution.christian.cleantest.event.presentation.overview.OverviewFragment


class EventFlowCoordinatorImpl : BaseCoordinatorImpl(), EventFlowCoordinator {

   override fun onDeepLinkBack() {
      when (currentFragment) {
         is DetailFragment -> showOverview()
         else -> activity.finish()
      }
   }

   override fun showDetail(
         userId: String,
         transitionInformation: BaseFragment.TransitionInformation?
   ) {
      currentFragment = DetailFragment.newInstance(userId, transitionInformation)

      if (transitionInformation != null) {
         activity.replaceFragmentwithSharedElement(
               currentFragment,
               replaceableFragmentId,
               DetailFragment.TAG,
               transitionInformation.sharedElement,
               transitionInformation.transitionName
         )
      } else {
         activity.replaceFragment(
               currentFragment,
               replaceableFragmentId,
               DetailFragment.TAG
         )
      }
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
                  DeepLinkIdentifier.EVENT_DETAIL -> showDetail(it.parameter!!)
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