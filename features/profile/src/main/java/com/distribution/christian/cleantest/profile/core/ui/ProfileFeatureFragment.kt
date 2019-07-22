package com.distribution.christian.cleantest.profile.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.navigation.FeatureStates
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.profile.R


class ProfileFeatureFragment : BaseFeatureFragment<BaseNavigationActivity>(FeatureStates.PROFILE) {

   companion object {

      const val TAG = "ProfileFeature"
      fun newInstance() = ProfileFeatureFragment()
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.setBottomNavigationItem(FeatureStates.PROFILE)
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      return inflater.inflate(R.layout.fragment_profile_main, container, false)
   }
}