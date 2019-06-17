package com.distribution.christian.cleantest.shop.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.shop.core.di.shopCoreModule
import com.distribution.christian.cleantest.shop.core.navigation.ShopFlowCoordinatorImpl
import com.distribution.christian.cleantest.shop.presentation.overview.OverviewFragment
import com.example.christian.cleantest.shop.R
import org.koin.android.ext.android.inject
import org.koin.standalone.StandAloneContext

private val loadFeature by lazy { StandAloneContext.loadKoinModules(shopCoreModule) }
private fun injectFeature() = loadFeature

class ShopFeatureFragment : BaseFeatureFragment<BaseNavigationActivity>() {

   override val coordinator: ShopFlowCoordinatorImpl by inject()

   companion object {

      const val TAG = "ShopFeature"
      fun newInstance() = ShopFeatureFragment()
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

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      coordinator.start(this, false)
      replaceFragment(
            OverviewFragment.newInstance(),
            R.id.fragment_container,
            OverviewFragment.TAG
      )
   }

   override fun onAttach(context: Context?) {
      super.onAttach(context)
      injectFeature()
   }


   override fun onHiddenChanged(hidden: Boolean) {
      super.onHiddenChanged(hidden)
      if (!hidden) {
         activity.activeFeatureFragment = this
      }
   }
}