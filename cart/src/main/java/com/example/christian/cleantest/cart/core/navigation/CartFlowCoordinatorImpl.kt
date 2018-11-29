package com.example.christian.cleantest.cart.core.navigation

import android.support.v4.app.FragmentActivity
import com.example.christian.cleantest.cart.R
import com.example.christian.cleantest.cart.presentation.cartview.DetailFragment
import com.example.christian.cleantest.cart.presentation.overview.OverviewFragment
import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.util.extension.backStack
import com.example.christian.cleantest.core.core.util.extension.replaceFragment

class CartFlowCoordinatorImpl : BaseCoordinator(), CartFlowCoordinator {

   override fun start(fragmentActivity: FragmentActivity) {
      super.start(fragmentActivity)
      //is there a deep link?

      //if not
      showOverview()
   }

   override fun back() {
      activity.backStack()
   }

   override fun showDetail(userId: String) {
      activity.replaceFragment(DetailFragment.newInstance(userId), R.id.fragment_container, DetailFragment.TAG)
   }

   override fun showOverview() {
      activity.replaceFragment(OverviewFragment.newInstance(), R.id.fragment_container, OverviewFragment.TAG)
   }
}