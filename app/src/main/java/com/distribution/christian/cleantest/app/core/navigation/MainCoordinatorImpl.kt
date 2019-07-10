package com.distribution.christian.cleantest.app.core.navigation

import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.navigation.FrankenCoordinatorManager
import com.distribution.christian.cleantest.core.core.navigation.FrankenDeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.navigation.coordinator.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.util.extension.navigateToAuth
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.event.core.ui.EventFeatureFragment
import com.distribution.christian.cleantest.profile.core.ui.ProfileFeatureFragment
import com.google.firebase.auth.FirebaseAuth
import org.koin.standalone.inject


class MainCoordinatorImpl : BaseCoordinatorImpl() {

   override var replaceableFragmentId: Int = R.id.feature_container

   private val firebaseAuth: FirebaseAuth by inject()

   init {
      firebaseAuth.addAuthStateListener {
         if (it.currentUser == null) {
            this.currentFeatureFragment?.run {
               activity?.run {
                  navigateToAuth(this)
               }
            }
            this.activity?.run {
               navigateToAuth(this)
            }
         }
      }
   }

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  FrankenDeepLinkIdentifier.EVENTS-> showEvents()
                  FrankenDeepLinkIdentifier.SHOP -> showShop()
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
