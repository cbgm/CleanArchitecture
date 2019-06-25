package com.distribution.christian.cleantest.core.core.navigation

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
   private val firebaseAuth: FirebaseAuth by inject()
   protected var isDeepLinkActive: Boolean = false

   protected var activity: FragmentActivity? = null
   protected var currentChildFragment: Fragment? = null
   protected var currentFeatureFragment: Fragment? = null
   open var replaceableFragmentId = R.id.fragment_container

   init {
      firebaseAuth.addAuthStateListener {
         /*if (it.currentUser == null) {
            this.currentFeatureFragment?.run {
               activity?.navigateToAuth(this.activity!!)
            }
            this.activity?.run {
               navigateToAuth(this)
            }
         }*/
      }
   }

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
      uri?.let { this.deepLinkHandler.setDeepLinks(it) }
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
}