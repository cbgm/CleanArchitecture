package com.example.christian.cleantest.core.core.navigation

import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.christian.cleantest.core.R
import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.example.christian.cleantest.core.core.util.extension.backStack
import kotlinx.android.synthetic.main.activity_main.view.fragment_container
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseCoordinatorImpl : BaseCoordinator, KoinComponent {

   protected val deepLinkHandler: DeepLinkHandler by inject()
   protected lateinit var currentFragment: Fragment
   protected var isDeepLinkActive: Boolean = false

   protected lateinit var activity: FragmentActivity
   protected var replaceableFragmentId = R.id.fragment_container

   override fun start(fragmentActivity: FragmentActivity) {
      activity = fragmentActivity
      initialNavigation()
   }

   override fun start(fragmentActivity: FragmentActivity, uri: Uri?) {
      activity = fragmentActivity
      uri?.let { deepLinkHandler.setDeepLinks(it) }
      initialNavigation()
   }

   override fun initialNavigation() {
      if (deepLinkHandler.hasDeepLinks())
         navigateDeepLink()
      else
         navigateLink()
   }

   override fun back() {
      if (isDeepLinkActive) {
         onDeepLinkBack()
      } else {
         activity.backStack()
      }
   }

   abstract override fun onDeepLinkBack()

   abstract override fun navigateLink()

   abstract override fun navigateDeepLink()
}