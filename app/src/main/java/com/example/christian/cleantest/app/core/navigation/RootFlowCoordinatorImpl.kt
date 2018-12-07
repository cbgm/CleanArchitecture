package com.example.christian.cleantest.app.core.navigation

import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.example.christian.cleantest.core.core.util.extension.navigateToCart
import com.example.christian.cleantest.core.core.util.extension.navigateToshop

class RootFlowCoordinatorImpl : BaseCoordinator() {

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  DeepLinkHandler.DeepLinkIdentifier.CART-> activity.navigateToCart()
                  DeepLinkHandler.DeepLinkIdentifier.SHOP -> activity.navigateToshop()
                  else -> activity.navigateToCart()
               }
            }
   }

   override fun navigateLink() {
      activity.navigateToCart()
   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
