package com.distribution.christian.cleantest.app.core.navigation

import androidx.fragment.app.Fragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.auth.core.ui.AuthFeatureFragment
import com.distribution.christian.cleantest.core.core.navigation.FeatureStates
import com.distribution.christian.cleantest.core.core.navigation.FrankenDeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.event.core.ui.EventFeatureFragment
import com.distribution.christian.cleantest.profile.core.ui.ProfileFeatureFragment
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.inject


class MainCoordinatorImpl : BaseCoordinatorImpl() {

   override var replaceableFragmentId: Int = R.id.feature_container

   private val firebaseAuth: FirebaseAuth by inject()

   init {
      firebaseAuth.addAuthStateListener {
         if (it.currentUser == null) {
            this.currentFeatureFragment?.run {
               activity?.run {
                  showAuth()
               }
            }
            this.activity?.run {
               showAuth()
            }
         }
      }
   }

   override fun navigateDeepLink(deepLink: DeepLink) {
      when (deepLink.action) {
         FrankenDeepLinkIdentifier.EVENTS -> showEvents()
         FrankenDeepLinkIdentifier.SHOP -> showShop()
         else -> showEvents()
      }

   }

   private fun showAuth() {
      activity?.replaceFragment(
            AuthFeatureFragment.newInstance(),
            replaceableFragmentId,
            AuthFeatureFragment.TAG
      )
   }

   private fun showEvents() {
      activity?.replaceFragment(
            EventFeatureFragment.newInstance(),
            replaceableFragmentId,
            EventFeatureFragment.TAG
      )
   }

   private fun showProfile() {
      activity?.replaceFragment(
            ProfileFeatureFragment.newInstance(),
            replaceableFragmentId,
            ProfileFeatureFragment.TAG
      )
   }

   private fun showShop() {
      activity?.replaceFragment(
            Class.forName("com.distribution.christian.cleantest.shop.core.ui.ShopFeatureFragment").newInstance() as BaseFeatureFragment<*>,
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

   override fun route(
         routeKey: CoordinatorManager.State,
         navigationData: CoordinatorManager.NavigationData?
   ): Fragment? {
      when (routeKey) {
         FeatureStates.EVENTS -> showEvents()
         FeatureStates.SHOP -> showShop()
         FeatureStates.PROFILE -> showProfile()
         FeatureStates.AUTH -> showAuth()
      }
      return null
   }
}
