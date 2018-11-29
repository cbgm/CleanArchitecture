package com.example.christian.cleantest.shop.presentation.overview

import android.os.Bundle
import android.view.View
import com.example.christian.cleantest.shop.R
import com.example.christian.cleantest.shop.core.ui.ShopBaseFragment


class OverviewFragment : ShopBaseFragment() {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_overview
   }

   override fun initViews(view: View) {
   }
}