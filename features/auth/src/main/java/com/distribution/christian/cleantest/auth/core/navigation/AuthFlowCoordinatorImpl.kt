package com.distribution.christian.cleantest.auth.core.navigation

import androidx.fragment.app.Fragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.presentation.login.LoginFragment
import com.distribution.christian.cleantest.auth.presentation.register.RegisterFragment
import com.distribution.christian.cleantest.auth.presentation.reset.ResetFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment


class AuthFlowCoordinatorImpl : BaseCoordinatorImpl(), AuthFlowCoordinator {
   override var replaceableFragmentId = R.id.fragment_container

   enum class States: CoordinatorManager.State {
      LOGIN,
      REGISTER,
      RESET
   }

   override fun navigateDeepLink(deepLink: DeepLink) {}

   override fun showLogin() {
      this.currentChildFragment = LoginFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            LoginFragment.TAG
      )
   }

   override fun showRegister() {
      this.currentChildFragment = RegisterFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            RegisterFragment.TAG
      )
   }

   override fun showReset() {
      this.currentChildFragment = ResetFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            ResetFragment.TAG
      )
   }

   override fun start(fragment: Fragment, withInitialNavigation: Boolean) {
      super.start(fragment, false)
      showLogin()
   }

   override fun navigateLink() {}

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
      when (routeKey) {
         States.LOGIN -> showLogin()
         States.REGISTER -> showRegister()
         States.RESET -> showReset()
      }
      return null
   }
}
