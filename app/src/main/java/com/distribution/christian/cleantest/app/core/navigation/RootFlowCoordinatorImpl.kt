package com.distribution.christian.cleantest.app.core.navigation

import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.coordinator.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.util.extension.navigateToAuth
import com.distribution.christian.cleantest.core.core.util.extension.navigateToMain
import com.distribution.christian.cleantest.core.core.util.extension.navigateToSplash


class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   enum class States: CoordinatorManager.State {
      SPLASH,
      AUTH,
      MAIN
   }

   override fun navigateDeepLink() {
      showAuthentication()
   }

   override fun navigateLink() {
      showAuthentication()
   }

   private fun showMain(){
      activity?.run {
         navigateToMain(this)
      }
   }

   private fun showAuthentication() {
      activity?.run {
         navigateToAuth(this)
         overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
      }
   }

   override fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean) {
      super.start(fragmentActivity, false)
      showAuthentication()
   }

   private fun showSplash() {
      activity?.run {
         navigateToSplash(this)
      }
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?) {
      when(routeKey) {
         States.SPLASH -> showSplash()
         States.AUTH -> showAuthentication()
         States.MAIN -> showMain()
      }
   }
}
