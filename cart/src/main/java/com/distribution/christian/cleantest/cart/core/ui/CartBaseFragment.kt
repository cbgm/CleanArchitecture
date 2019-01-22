package com.distribution.christian.cleantest.cart.core.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.cart.core.navigation.CartFlowCoordinatorImpl
import org.koin.android.ext.android.inject

abstract class CartBaseFragment : Fragment() {

   val cartFlowCoordinator: CartFlowCoordinatorImpl by inject()

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      val view = inflater.inflate(getLayoutResId(), container, false)
      initViews(view)
      return view
   }

   abstract fun initViews(view: View)

   abstract fun getLayoutResId(): Int
}