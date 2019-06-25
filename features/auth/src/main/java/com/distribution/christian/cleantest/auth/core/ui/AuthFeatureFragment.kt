package com.distribution.christian.cleantest.auth.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl
import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.ui.BaseStandaloneActivity
import org.koin.android.ext.android.inject


class AuthFeatureFragment : BaseFeatureFragment<BaseStandaloneActivity>() {

   //override val coordinator: AuthFlowCoordinatorImpl by inject()

   companion object {

      const val TAG = "AuthFeature"
      //fun newInstance() = AuthFeatureFragment()
   }

   override fun onResume() {
      super.onResume()
      activity.activeFeatureFragment = this
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      return inflater.inflate(R.layout.fragment_auth_main, container, false)
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      //coordinator.start(this, false)
      //coordinator.showLogin()
   }
}