package com.distribution.christian.cleantest.auth.presentation.register

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.ui.AuthBaseFragment
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.core.util.listener.OnTextChangedListener
import org.koin.android.ext.android.inject


class RegisterFragment : AuthBaseFragment(), RegisterContract.View {

   companion object {
      const val TAG = "Register"
      fun newInstance() = RegisterFragment()
   }

   private val presenter: RegisterPresenter by inject()

   private lateinit var registerBtn: TextView
   private lateinit var backBtn: TextView
   private lateinit var emailText: EditText
   private lateinit var passwordText: EditText
   private lateinit var retypePasswordText: EditText

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.updateScope(DiScope.AUTH_REGISTER)
      presenter.setVIew(this)
   }

   override fun onResume() {
      super.onResume()
      presenter.onBind()
   }

   override fun initViews(view: View) {
      registerBtn = view.findViewById(R.id.register_btn)
      backBtn = view.findViewById(R.id.back_btn)
      passwordText = view.findViewById(R.id.password_text)
      retypePasswordText = view.findViewById(R.id.retype_password_text)
      emailText = view.findViewById(R.id.email_text)

      emailText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            presenter.validateRegistrationData(
                  emailText.text.toString(),
                  passwordText.text.toString(),
                  retypePasswordText.text.toString()
            )
         }
      })

      passwordText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            presenter.validateRegistrationData(
                  emailText.text.toString(),
                  passwordText.text.toString(),
                  retypePasswordText.text.toString()
            )
         }
      })


      retypePasswordText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            presenter.validateRegistrationData(
                  emailText.text.toString(),
                  passwordText.text.toString(),
                  retypePasswordText.text.toString()
            )
         }
      })

      registerBtn.setOnClickListener {
         presenter.addUser(
               emailText.text.toString(),
               passwordText.text.toString()
         )
      }

      backBtn.setOnClickListener {
         activity.coordinator.back()
      }
   }

   override fun showAddedUserFailure() {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun showRegisterButtonEnabled(isEnabled: Boolean) {
      registerBtn.isEnabled = isEnabled
   }

   override fun showAddedUserSuccess() {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_register
   }

   override fun showContent(isVisible: Boolean) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun showError(isVisible: Boolean) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun showLoading(isVisible: Boolean) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
   }
}