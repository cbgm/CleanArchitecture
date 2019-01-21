package com.example.christian.cleantest.shop.core.ui

import android.content.Context
import android.os.Bundle
import com.example.christian.cleantest.core.R
import com.example.christian.cleantest.core.core.ui.BaseSplitActivity
import com.example.christian.cleantest.shop.core.di.shopCoreModule
import com.example.christian.cleantest.shop.core.navigation.ShopFlowCoordinatorImpl
import org.koin.android.ext.android.inject
import org.koin.standalone.StandAloneContext.loadKoinModules

private val loadFeature by lazy { loadKoinModules(shopCoreModule) }
private fun injectFeature() = loadFeature

class ShopActivity : BaseSplitActivity() {

   override val coordinator: ShopFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      bottomNavigationView.menu.findItem(R.id.action_shop).isChecked = true
      coordinator.start(this)
   }

   override fun onBackPressed() {
      coordinator.back()
   }

   override fun attachBaseContext(newBase: Context?) {
      super.attachBaseContext(newBase)
      injectFeature()
   }
}