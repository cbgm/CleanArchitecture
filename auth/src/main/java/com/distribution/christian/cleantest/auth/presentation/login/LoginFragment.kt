package com.distribution.christian.cleantest.auth.presentation.login

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.distribution.christian.cleantest.core.core.util.extension.navigateToEvents
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.ui.AuthBaseFragment
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.widget.CompoundButton
import android.widget.CheckBox


class LoginFragment : AuthBaseFragment() {

   companion object {
      const val TAG = "Login"
      fun newInstance() = LoginFragment()
   }

   private lateinit var loginBtn: TextView
   private lateinit var registerBtn: TextView
   private lateinit var resetBtn: TextView
   private lateinit var emailText: TextView
   private lateinit var passwordText: TextView
   private lateinit var showPasswordCheck: CheckBox

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {

      val view = super.onCreateView(inflater, container, savedInstanceState)
      configureTransition(view)
      return view
   }

   override fun initViews(view: View) {
      loginBtn = view.findViewById(R.id.login_btn)
      registerBtn = view.findViewById(R.id.register_btn)
      resetBtn = view.findViewById(R.id.reset_btn)
      emailText = view.findViewById(R.id.email_text)
      passwordText = view.findViewById(R.id.password_text)
      showPasswordCheck = view.findViewById(R.id.show_password_check)

      showPasswordCheck.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
         if (isChecked) {
            passwordText.transformationMethod = HideReturnsTransformationMethod.getInstance()
         } else {
            passwordText.transformationMethod = PasswordTransformationMethod.getInstance()
         }
      })

      resetBtn.setOnClickListener {
         activity.coordinator.showReset()
      }

      registerBtn.setOnClickListener {
         activity.coordinator.showRegister()
      }

      loginBtn.setOnClickListener {
         activity.navigateToEvents(activity)
      }
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_login
   }

   private fun configureTransition(view: View?) {
      view!!.findViewById<TextView>(R.id.header_text).transitionName = "test"
      sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.default_transition)
      enterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.no_transition)
   }
}