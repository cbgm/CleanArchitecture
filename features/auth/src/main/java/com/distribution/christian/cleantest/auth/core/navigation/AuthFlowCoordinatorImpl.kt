package com.distribution.christian.cleantest.auth.core.navigation

import androidx.fragment.app.Fragment
import com.distribution.christian.cleantest.core.core.navigation.coordinator.BaseCoordinatorImpl
import com.distribution.christian.cleantest.auth.presentation.login.LoginFragment
import com.distribution.christian.cleantest.auth.presentation.register.RegisterFragment
import com.distribution.christian.cleantest.auth.presentation.reset.ResetFragment
import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.util.extension.navigateToMain
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment


class AuthFlowCoordinatorImpl : BaseCoordinatorImpl(), AuthFlowCoordinator {

   enum class States: CoordinatorManager.State {
      LOGIN,
      REGISTER,
      RESET
   }

   override fun navigateDeepLink() {
      currentFeatureFragment?.activity?.run {
         navigateToMain(this)
      }
   }

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

   override fun navigateLink() {
      currentFeatureFragment?.activity?.run {
         navigateToMain(this)
      }
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?) {
      when (routeKey) {
         States.LOGIN -> showLogin()
         States.REGISTER -> showRegister()
         States.RESET -> showReset()
      }
   }
}
