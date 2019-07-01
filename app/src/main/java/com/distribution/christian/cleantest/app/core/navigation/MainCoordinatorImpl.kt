package com.distribution.christian.cleantest.app.core.navigation

import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.CoordinatorManager
import com.distribution.christian.cleantest.core.core.navigation.FrankenCoordinatorManager
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.event.core.ui.EventFeatureFragment
import com.distribution.christian.cleantest.profile.core.ui.ProfileFeatureFragment


class MainCoordinatorImpl : BaseCoordinatorImpl() {

   override var replaceableFragmentId: Int = R.id.feature_container

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  DeepLinkIdentifier.EVENTS-> showEvents()
                  DeepLinkIdentifier.SHOP -> showShop()
                  else -> showEvents()
               }
            }
   }

   private fun showEvents() {
      activity?.replaceFragment(
            EventFeatureFragment.newInstance(),
            replaceableFragmentId,
            EventFeatureFragment.TAG
      )
   }

   private fun showProfile(){
      activity?.replaceFragment(
            ProfileFeatureFragment.newInstance(),
            replaceableFragmentId,
            ProfileFeatureFragment.TAG
      )
   }

   private fun showShop(){
      activity?.replaceFragment(
            Class.forName("com.distribution.christian.cleantest.shop.core.ui.ShopFeatureFragment").newInstance() as BaseFeatureFragment<BaseNavigationActivity>,
            replaceableFragmentId,
            "dyn"
      )
   }

   override fun navigateLink() {
      showEvents()
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?) {
      when(routeKey) {
         FrankenCoordinatorManager.States.EVENTS -> showEvents()
         FrankenCoordinatorManager.States.SHOP -> showShop()
         FrankenCoordinatorManager.States.PROFILE -> showProfile()
      }
   }
}
