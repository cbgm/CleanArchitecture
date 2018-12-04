package com.example.christian.cleantest.core.core.navigation

import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.example.christian.cleantest.core.core.util.extension.backStack
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseCoordinator : KoinComponent {

   val deepLinkHandler: DeepLinkHandler by inject()
   lateinit var currentFragment: Fragment
   var isDeepLinkActive: Boolean = false

   lateinit var activity: FragmentActivity

   open fun start(fragmentActivity: FragmentActivity) {
      activity = fragmentActivity
      initialNavigation()
   }

   open fun start(fragmentActivity: FragmentActivity, uri: Uri?) {
      activity = fragmentActivity
      uri?.let { deepLinkHandler.setDeepLinks(it) }
      initialNavigation()
   }

   private fun initialNavigation() {
      if (deepLinkHandler.hasDeepLinks())
         navigateDeepLink()
      else
         navigateLink()
   }

   fun back() {
      if (isDeepLinkActive) {
         onDeepLinkBack()
      } else {
         activity.backStack()
      }
   }

   abstract fun onDeepLinkBack()

   abstract fun navigateLink()

   abstract fun navigateDeepLink()
}