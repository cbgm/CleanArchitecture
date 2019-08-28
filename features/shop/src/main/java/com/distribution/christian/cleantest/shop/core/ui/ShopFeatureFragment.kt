package com.distribution.christian.cleantest.shop.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.navigation.FeatureStates
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.shop.core.di.shopCoreModule
import com.example.christian.cleantest.shop.R
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.core.context.loadKoinModules

private val loadFeature by lazy { loadKoinModules(shopCoreModule) }
private fun injectFeature() = loadFeature

class ShopFeatureFragment : BaseFeatureFragment<BaseNavigationActivity>(FeatureStates.SHOP) {

   companion object {

      const val TAG = "ShopFeature"
      fun newInstance() = ShopFeatureFragment()
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.setBottomNavigationItem(FeatureStates.SHOP)
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
      SplitCompat.install(context)
   }
}