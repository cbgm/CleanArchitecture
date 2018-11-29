package com.example.christian.cleantest.shop.core.navigation

import android.support.v4.app.FragmentActivity
import com.example.christian.cleantest.shop.R
import com.example.christian.cleantest.shop.presentation.overview.OverviewFragment
import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.util.extension.replaceFragment

class ShopFlowCoordinatorImpl : BaseCoordinator(), ShopFlowCoordinator {

   override fun start(fragmentActivity: FragmentActivity) {
      super.start(fragmentActivity)
      //is there a deep link?

      //if not
      showOverview()
   }

   override fun back() {
      activity.onBackPressed()
   }

   override fun showOverview() {
      activity.replaceFragment(OverviewFragment.newInstance(), R.id.fragment_container, OverviewFragment.TAG)
   }
}