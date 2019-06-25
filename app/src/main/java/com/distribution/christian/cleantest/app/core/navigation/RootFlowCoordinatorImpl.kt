package com.distribution.christian.cleantest.app.core.navigation

import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.util.extension.navigateToAuth
import com.distribution.christian.cleantest.core.core.util.extension.navigateToMain


class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   override fun navigateDeepLink() {
      //not needed
   }

   override fun navigateLink() {
      //not needed
   }

   fun showAuthentication() {
      activity?.navigateToMain(activity!!)
      //activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
