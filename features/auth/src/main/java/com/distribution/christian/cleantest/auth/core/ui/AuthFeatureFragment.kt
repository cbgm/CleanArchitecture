package com.distribution.christian.cleantest.auth.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.core.core.navigation.FeatureStates
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity


class AuthFeatureFragment : BaseFeatureFragment<BaseNavigationActivity>(FeatureStates.AUTH) {

   companion object {

      const val TAG = "AuthFeature"
      fun newInstance() = AuthFeatureFragment()
   }

   override fun onResume() {
      super.onResume()
      activity.hideNavigationElements()
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      return inflater.inflate(R.layout.fragment_auth_main, container, false)
   }
}