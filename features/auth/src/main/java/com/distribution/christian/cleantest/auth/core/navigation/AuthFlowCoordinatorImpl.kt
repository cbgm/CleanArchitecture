package com.distribution.christian.cleantest.auth.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.auth.presentation.login.LoginFragment
import com.distribution.christian.cleantest.auth.presentation.register.RegisterFragment
import com.distribution.christian.cleantest.auth.presentation.reset.ResetFragment
import com.distribution.christian.cleantest.core.core.util.extension.navigateToMain
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment


class AuthFlowCoordinatorImpl : BaseCoordinatorImpl(), AuthFlowCoordinator {

   override fun navigateDeepLink() {
      currentFeatureFragment?.activity?.navigateToMain(currentFeatureFragment?.activity!!)
   }

   override fun showLogin() {
      this.currentChildFragment = LoginFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment!!,
            replaceableFragmentId,
            LoginFragment.TAG
      )
   }

   override fun showRegister() {
      this.currentChildFragment = RegisterFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment!!,
            replaceableFragmentId,
            RegisterFragment.TAG
      )
   }

   override fun showReset() {
      this.currentChildFragment = ResetFragment.newInstance()
      currentFeatureFragment?.replaceFragment(
            this.currentChildFragment!!,
            replaceableFragmentId,
            ResetFragment.TAG
      )
   }

   override fun navigateLink() {
      currentFeatureFragment?.activity!!.navigateToMain(currentFeatureFragment?.activity!!)
   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
