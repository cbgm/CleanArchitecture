package com.distribution.christian.cleantest.event.core.navigation

import androidx.fragment.app.Fragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.distribution.christian.cleantest.core.core.navigation.FrankenDeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.navigation.NavigationData
import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragmentwithSharedElement
import com.distribution.christian.cleantest.core.core.util.extension.showStarsDialog
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.presentation.detail.DetailFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventDetailFragmentConsistency
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.OverviewFragment


class EventFlowCoordinatorImpl : BaseCoordinatorImpl(), EventFlowCoordinator {
   override var replaceableFragmentId = R.id.fragment_container

   enum class States : CoordinatorManager.State {
      OVERVIEW,
      DETAIL,
      STARS
   }

   override fun onDeepLinkBack() {
      when (this.currentChildFragment) {
         is DetailFragment -> showOverview()
         else -> currentFeatureFragment?.activity?.finish()
      }
   }

   override fun showDetail(
         userId: String?,
         transitionInformation: BaseFragment.TransitionInformation?,
         event: EventEntity?
   ) {
      this.currentChildFragment = DetailFragment.newInstance(userId, transitionInformation, event)

      if (transitionInformation != null) {
         currentFeatureFragment?.replaceFragmentwithSharedElement(
               this.currentChildFragment,
               replaceableFragmentId,
               DetailFragment.TAG,
               transitionInformation.sharedElement,
               transitionInformation.transitionName
         )
      } else {
         currentFeatureFragment?.replaceFragment(
               this.currentChildFragment,
               replaceableFragmentId,
               DetailFragment.TAG
         )
      }
   }

   override fun showOverview() {
      this.currentChildFragment = OverviewFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            OverviewFragment.TAG
      )
   }

   override fun showStars() {
      currentFeatureFragment?.activity?.showStarsDialog(currentFeatureFragment!!)
   }

   override fun navigateDeepLink(deepLink: DeepLink) {
      when (deepLink.action) {
         FrankenDeepLinkIdentifier.EVENT_DETAIL -> showDetail(deepLink.parameter!!)
         else -> {
            //not needed
         }
      }
      isDeepLinkActive = true

   }

   override fun navigateLink() {
      showOverview()
   }

   override fun route(
         routeKey: CoordinatorManager.State,
         navigationData: CoordinatorManager.NavigationData?
   ): Fragment? {
      when (routeKey) {
         States.OVERVIEW -> showOverview()
         States.DETAIL -> (navigationData as NavigationData).let {
            showDetail(
                  it.params!![EventDetailFragmentConsistency.Event_ID_KEY].toString(),
                  it.transitionInformation,
                  it.params!![EventDetailFragmentConsistency.EVENT_KEY] as EventEntity
            )
         }
         States.STARS -> showStars()
      }
      return null
   }
}