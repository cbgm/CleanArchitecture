package com.example.christian.cleantest.cart.core.ui

import android.os.Bundle
import android.view.MenuItem
import com.example.christian.cleantest.cart.core.navigation.CartFlowCoordinatorImpl
import com.example.christian.cleantest.core.R
import com.example.christian.cleantest.core.core.ui.BaseActivity

import org.koin.android.ext.android.inject

class CartActivity: BaseActivity() {

   private val cartFlowCoordinator: CartFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      bottomNavigationView.menu.findItem(R.id.action_cart).isChecked = true
      cartFlowCoordinator.start(this)
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {
      return when (item?.itemId) {
         android.R.id.home -> {
            cartFlowCoordinator.back()
            true
         }
         else -> false
      }
   }
}