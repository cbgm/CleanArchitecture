package com.distribution.christian.cleantest.core.core.navigation.coordinator

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.core.R
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.distribution.christian.cleantest.core.core.util.extension.backStack
import com.distribution.christian.cleantest.core.core.util.extension.backStackClean
import com.distribution.christian.cleantest.core.core.util.extension.navigateToAuth
import com.google.firebase.auth.FirebaseAuth
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


abstract class BaseCoordinatorImpl : BaseCoordinator, KoinComponent {

   protected val deepLinkHandler: DeepLinkHandler by inject()
   protected var isDeepLinkActive: Boolean = false

   protected var activity: FragmentActivity? = null
   protected lateinit var currentChildFragment: Fragment
   protected var currentFeatureFragment: Fragment? = null
   open var replaceableFragmentId = R.id.fragment_container

   override fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean) {
      this.activity = fragmentActivity

      if (withInitialNavigation) {
         initialNavigation()
      }
   }

   override fun start(fragment: Fragment, withInitialNavigation: Boolean) {
      this.currentFeatureFragment = fragment

      if (withInitialNavigation) {
         initialNavigation()
      }
   }

   override fun start(fragmentActivity: FragmentActivity, uri: Uri?) {
      this.activity = fragmentActivity
      initialNavigation()
   }

   override fun initialNavigation() {
      if (this.deepLinkHandler.hasDeepLinks())
         navigateDeepLink()
      else
         navigateLink()
   }

   override fun back() {
      if (this.isDeepLinkActive) {
         onDeepLinkBack()
      } else {
         this.currentFeatureFragment?.backStack()
      }
   }

   override fun clear() {
      this.activity?.backStackClean()
   }

   abstract override fun onDeepLinkBack()

   abstract override fun navigateLink()

   abstract override fun navigateDeepLink()

   abstract override fun route(
         routeKey: CoordinatorManager.State,
         navigationData: CoordinatorManager.NavigationData?
   )
}