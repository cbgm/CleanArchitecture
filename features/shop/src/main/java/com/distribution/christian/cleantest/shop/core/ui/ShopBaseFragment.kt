package com.distribution.christian.cleantest.shop.core.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class ShopBaseFragment : Fragment() {

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