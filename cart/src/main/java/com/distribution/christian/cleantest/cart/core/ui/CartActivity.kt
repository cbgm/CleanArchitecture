package com.distribution.christian.cleantest.cart.core.ui

import android.os.Bundle
import android.view.MenuItem
import com.distribution.christian.cleantest.cart.core.navigation.CartFlowCoordinatorImpl
import com.distribution.christian.cleantest.core.R
import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import org.koin.android.ext.android.inject

class CartActivity: BaseActivity() {

   override val coordinator: CartFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      bottomNavigationView.menu.findItem(R.id.action_cart).isChecked = true
      coordinator.start(this)
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {
      return when (item?.itemId) {
         android.R.id.home -> {
            coordinator.back()
            true
         }
         else -> false
      }
   }
}