package com.distribution.christian.cleantest.event.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.navigation.EventFlowCoordinatorImpl
import org.koin.android.ext.android.inject


class EventFeatureFragment : BaseFeatureFragment<BaseNavigationActivity>() {

   override val coordinator: EventFlowCoordinatorImpl by inject()

   companion object {

      const val TAG = "EventFeature"
      fun newInstance() = EventFeatureFragment()
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
      return inflater.inflate(R.layout.fragment_event_main, container, false)
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      coordinator.start(this)
   }

   override fun onHiddenChanged(hidden: Boolean) {
      super.onHiddenChanged(hidden)
      if (!hidden) {
         activity.activeFeatureFragment = this
      }
   }
}