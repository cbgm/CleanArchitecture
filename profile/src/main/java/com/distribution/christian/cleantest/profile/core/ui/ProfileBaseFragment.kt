package com.distribution.christian.cleantest.profile.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.ui.BaseFragment

abstract class ProfileBaseFragment : BaseFragment<ProfileActivity>() {

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