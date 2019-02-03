package com.distribution.christian.cleantest.auth.presentation.register

import android.view.View
import android.widget.TextView
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.ui.AuthBaseFragment


class RegisterFragment : AuthBaseFragment() {

   companion object {
      const val TAG = "Register"
      fun newInstance() = RegisterFragment()
   }

   private lateinit var registerBtn: TextView
   private lateinit var backBtn: TextView

   override fun initViews(view: View) {
      backBtn = view.findViewById(R.id.back_btn)
      backBtn.setOnClickListener {
         activity.coordinator.back()
      }
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_register
   }
}