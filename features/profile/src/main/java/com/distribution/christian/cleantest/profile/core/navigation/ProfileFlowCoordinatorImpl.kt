package com.distribution.christian.cleantest.profile.core.navigation

import androidx.fragment.app.Fragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.core.core.util.extension.showFeedbackDialog
import com.distribution.christian.cleantest.core.core.util.extension.showHelpDialog
import com.distribution.christian.cleantest.core.core.util.extension.showPrivacyDialog
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.presentation.help.HelpFragment
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewFragment
import com.distribution.christian.cleantest.profile.presentation.settings.SettingsFragment


class ProfileFlowCoordinatorImpl : BaseCoordinatorImpl(), ProfileFlowCoordinator {
   override var replaceableFragmentId = R.id.fragment_container

   enum class States : CoordinatorManager.State {
      OVERVIEW,
      SETTINGS,
      FEEDBACK,
      HELP
   }

   override fun showOverview() {
      this.currentChildFragment = OverviewFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            OverviewFragment.TAG
      )
   }

   override fun showSettings() {
      this.currentChildFragment = SettingsFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            SettingsFragment.TAG
      )
   }

   override fun showHelp() {
      currentFeatureFragment?.activity?.showHelpDialog(currentFeatureFragment!!)
   }

   override fun showFeedback() {
      currentFeatureFragment?.activity?.showFeedbackDialog(currentFeatureFragment!!)
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

   override fun route(
         routeKey: CoordinatorManager.State,
         navigationData: CoordinatorManager.NavigationData?
   ): Fragment? {
      when (routeKey) {
         States.OVERVIEW -> showOverview()
         States.SETTINGS -> showSettings()
         States.FEEDBACK -> showFeedback()
         States.HELP -> showHelp()
      }
      return null
   }
}