package com.example.christian.cleantest.core.core.navigation

import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.example.christian.cleantest.core.core.util.extension.backStack
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

interface BaseCoordinator : KoinComponent {

   fun start(fragmentActivity: FragmentActivity)

   fun start(fragmentActivity: FragmentActivity, uri: Uri?)

   fun initialNavigation()

   fun back()

   fun onDeepLinkBack()

   fun navigateLink()

   fun navigateDeepLink()
}