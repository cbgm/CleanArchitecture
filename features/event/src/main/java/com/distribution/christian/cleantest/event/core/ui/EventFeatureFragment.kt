package com.distribution.christian.cleantest.event.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.navigation.FeatureStates
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.event.R
import org.koin.android.ext.android.inject


class EventFeatureFragment : BaseFeatureFragment<BaseNavigationActivity>(FeatureStates.EVENTS) {

   companion object {

      const val TAG = "EventFeature"
      fun newInstance() = EventFeatureFragment()
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.activeFeatureFragment = this
      activity.setBottomNavigationItem(FeatureStates.EVENTS)
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      return inflater.inflate(R.layout.fragment_event_main, container, false)
   }
}