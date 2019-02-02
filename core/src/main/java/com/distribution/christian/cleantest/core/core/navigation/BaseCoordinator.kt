package com.distribution.christian.cleantest.core.core.navigation

import android.net.Uri
import android.support.v4.app.FragmentActivity
import org.koin.standalone.KoinComponent


interface BaseCoordinator : KoinComponent {

   fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean = true)

   fun start(fragmentActivity: FragmentActivity, uri: Uri?)

   fun initialNavigation()

   fun back()

   fun onDeepLinkBack()

   fun navigateLink()

   fun navigateDeepLink()
}