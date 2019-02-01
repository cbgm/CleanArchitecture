package com.distribution.christian.cleantest.login.presentation.login

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import com.distribution.christian.cleantest.core.core.util.extension.navigateToEvents
import com.distribution.christian.cleantest.login.R
import com.distribution.christian.cleantest.login.core.ui.AuthBaseFragment

class LoginFragment : AuthBaseFragment() {

   companion object {
      const val TAG = "Login"
      fun newInstance() = LoginFragment()
   }

   private lateinit var loginBtn: TextView

   override fun initViews(view: View) {
      loginBtn = view.findViewById(R.id.login_btn)
      loginBtn.setOnClickListener {
         activity.navigateToEvents(activity)
      }
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {

      val view = super.onCreateView(inflater, container, savedInstanceState)
      view!!.findViewById<TextView>(R.id.header_text).transitionName = "test"
      sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.default_transition)
      enterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.no_transition)
      return view
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
   }


   override fun getLayoutResId(): Int {
      return R.layout.fragment_login
   }

}