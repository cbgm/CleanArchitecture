package com.distribution.christian.cleantest.event.core.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.event.core.navigation.EventFlowCoordinatorImpl
import org.koin.android.ext.android.inject

abstract class EventBaseFragment : BaseFragment() {

   val cartFlowCoordinator: EventFlowCoordinatorImpl by inject()

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