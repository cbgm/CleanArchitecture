package com.distribution.christian.cleantest.auth.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl
import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import org.koin.android.ext.android.inject


abstract class AuthBaseFragment : BaseFragment<AuthActivity, Unit>() {

   override val coordinator: AuthFlowCoordinatorImpl by inject()

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