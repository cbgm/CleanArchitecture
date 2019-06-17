package com.distribution.christian.cleantest.core.core.navigation

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.koin.standalone.KoinComponent


interface BaseCoordinator : KoinComponent {

   fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean = true)

   fun start(fragment: Fragment, withInitialNavigation: Boolean = true)

   fun start(fragmentActivity: FragmentActivity, uri: Uri?)

   fun initialNavigation()

   fun back()

   fun clear()

   fun onDeepLinkBack()

   fun navigateLink()

   fun navigateDeepLink()
}