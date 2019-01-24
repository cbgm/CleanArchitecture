package com.distribution.christian.cleantest.app.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.distribution.christian.cleantest.core.core.util.extension.navigateToEvents
import com.distribution.christian.cleantest.core.core.util.extension.navigateToShop

class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  DeepLinkHandler.DeepLinkIdentifier.EVENTS-> activity.navigateToEvents(activity)
                  DeepLinkHandler.DeepLinkIdentifier.SHOP -> activity.navigateToShop(activity)
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
