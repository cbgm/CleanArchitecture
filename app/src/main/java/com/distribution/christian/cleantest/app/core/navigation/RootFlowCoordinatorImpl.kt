package com.distribution.christian.cleantest.app.core.navigation

import android.view.View
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.util.extension.navigateToAuth


class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   override fun navigateDeepLink() {
      //not needed
   }

   override fun navigateLink() {
      //not needed
   }

   fun showAuthentication(sharedView: View) {
      activity.navigateToAuth(activity/*, sharedView*/)
      activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

   }

   override fun onDeepLinkBack() {
      //not needed
   }
}
