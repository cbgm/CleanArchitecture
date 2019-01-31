package com.distribution.christian.cleantest.app.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.util.extension.navigateToAuth

class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   override fun navigateDeepLink() {
      navigateLink()
   }

   override fun navigateLink() {
      activity.navigateToAuth(activity)
   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
