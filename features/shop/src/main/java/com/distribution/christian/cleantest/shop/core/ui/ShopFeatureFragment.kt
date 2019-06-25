package com.distribution.christian.cleantest.shop.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.shop.core.di.shopCoreModule
import com.example.christian.cleantest.shop.R
import org.koin.standalone.StandAloneContext

private val loadFeature by lazy { StandAloneContext.loadKoinModules(shopCoreModule) }
private fun injectFeature() = loadFeature

class ShopFeatureFragment : BaseFeatureFragment<BaseNavigationActivity>() {

   companion object {
      const val TAG = "ShopFeature"
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.activeFeatureFragment = this
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      return inflater.inflate(R.layout.fragment_shop_main, container, false)
   }

   override fun onAttach(context: Context) {
      super.onAttach(context)
      injectFeature()
   }
}