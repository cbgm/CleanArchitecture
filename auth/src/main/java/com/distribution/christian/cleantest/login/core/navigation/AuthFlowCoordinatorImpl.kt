package com.distribution.christian.cleantest.login.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.util.extension.navigateToEvents
import com.distribution.christian.cleantest.core.core.util.extension.navigateToShop

class AuthFlowCoordinatorImpl : BaseCoordinatorImpl() {

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

   override fun navigateLink() {
      activity.navigateToEvents(activity)
   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
