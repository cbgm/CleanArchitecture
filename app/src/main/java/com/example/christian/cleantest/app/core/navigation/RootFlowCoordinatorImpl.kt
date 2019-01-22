package com.example.christian.cleantest.app.core.navigation

import com.example.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.example.christian.cleantest.core.core.util.extension.navigateToCart
import com.example.christian.cleantest.core.core.util.extension.navigateToShop

class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  DeepLinkHandler.DeepLinkIdentifier.CART-> activity.navigateToCart(activity)
                  DeepLinkHandler.DeepLinkIdentifier.SHOP -> activity.navigateToShop(activity)
                  else -> activity.navigateToCart(activity)
               }
            }
   }

   override fun navigateLink() {
      activity.navigateToCart(activity)
   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
