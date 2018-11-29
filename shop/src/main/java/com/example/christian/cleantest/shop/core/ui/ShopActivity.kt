package com.example.christian.cleantest.shop.core.ui

import android.os.Bundle
import com.example.christian.cleantest.core.R
import com.example.christian.cleantest.core.core.ui.BaseActivity
import com.example.christian.cleantest.shop.core.navigation.ShopFlowCoordinatorImpl
import org.koin.android.ext.android.inject

class ShopActivity : BaseActivity() {

   private val shopFlowCoordinator: ShopFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      bottomNavigationView.menu.findItem(R.id.action_shop).isChecked = true
      shopFlowCoordinator.start(this)
   }
}