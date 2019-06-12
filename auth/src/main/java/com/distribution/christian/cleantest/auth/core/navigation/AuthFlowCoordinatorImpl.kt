package com.distribution.christian.cleantest.auth.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.util.extension.navigateToShop
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.auth.presentation.login.LoginFragment
import com.distribution.christian.cleantest.auth.presentation.register.RegisterFragment
import com.distribution.christian.cleantest.auth.presentation.reset.ResetFragment
import com.distribution.christian.cleantest.core.core.util.extension.navigateToEvents


class AuthFlowCoordinatorImpl : BaseCoordinatorImpl(), AuthFlowCoordinator {

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  DeepLinkIdentifier.EVENTS-> activity.navigateToEvents(activity)
                  DeepLinkIdentifier.SHOP -> activity.navigateToShop(activity)
                  else -> activity.navigateToEvents(activity)
               }
            }
   }

   override fun showLogin() {
      currentFragment = LoginFragment.newInstance()
      activity.replaceFragment(
            currentFragment,
            replaceableFragmentId,
            LoginFragment.TAG
      )
   }

   override fun showRegister() {
      currentFragment = RegisterFragment.newInstance()
      activity.replaceFragment(
            currentFragment,
            replaceableFragmentId,
            RegisterFragment.TAG
      )
   }

   override fun showReset() {
      currentFragment = ResetFragment.newInstance()
      activity.replaceFragment(
            currentFragment,
            replaceableFragmentId,
            ResetFragment.TAG
      )
   }

   override fun navigateLink() {
      activity.navigateToEvents(activity)
   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
