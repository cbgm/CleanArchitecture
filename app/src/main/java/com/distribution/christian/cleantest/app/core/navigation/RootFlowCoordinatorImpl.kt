package com.distribution.christian.cleantest.app.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.AppStates
import com.distribution.christian.cleantest.core.core.util.extension.navigateToMain
import com.distribution.christian.cleantest.core.core.util.extension.navigateToSplash


class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   override fun navigateDeepLink(deepLink: DeepLink) {
      showMain()
   }

   override fun navigateLink() {
      showMain()
   }

   private fun showMain(){
      activity?.run {
         navigateToMain(this)
         overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
      }
   }

   override fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean) {
      super.start(fragmentActivity, false)
      showMain()
   }

   private fun showSplash() {
      activity?.run {
         navigateToSplash(this)
      }
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
      when(routeKey) {
         AppStates.SPLASH -> showSplash()
         AppStates.MAIN -> showMain()
      }
      return null
   }
}
