package com.distribution.christian.cleantest.auth.presentation.register

import android.animation.Animator
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl.States.LOGIN
import com.distribution.christian.cleantest.auth.core.ui.AuthBaseFragment
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.core.util.listener.AnimationEndListener
import com.distribution.christian.cleantest.core.core.util.listener.OnTextChangedListener
import org.koin.android.ext.android.inject


class RegisterFragment : AuthBaseFragment(), RegisterContract.View {

   companion object {
      const val TAG = "Register"
      fun newInstance() = RegisterFragment()
   }

   private val presenter: RegisterPresenter by inject()

   private lateinit var registerBtn: LinearLayout
   private lateinit var registerBtnProgress: ProgressBar
   private lateinit var registerBtnText: TextView
   private lateinit var backBtn: TextView
   private lateinit var emailText: EditText
   private lateinit var passwordText: EditText
   private lateinit var retypePasswordText: EditText
   private lateinit var errorText: TextView
   private lateinit var validImage: ImageView

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
      registerBtnProgress = view.findViewById(R.id.register_loading)
      registerBtnText = view.findViewById(R.id.register_text)
      errorText = view.findViewById(R.id.error_text)
      validImage = view.findViewById(R.id.valid_img)


      registerBtn.isEnabled = false

      emailText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            validateCredentials()
         }
      })

      passwordText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            validateCredentials()
         }
      })


      retypePasswordText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            validateCredentials()
         }
      })

      registerBtn.setOnClickListener {
         presenter.addUser(
               emailText.text.toString(),
               passwordText.text.toString()
         )
      }

      backBtn.setOnClickListener {
         coordinatorManager.backStackFeature()
      }
   }

   override fun showAddedUserFailure() {
      //not used
   }

   override fun showEnabledRegisterButton(isEnabled: Boolean) {
      registerBtn.isEnabled = isEnabled
   }

   override fun showAddedUserSuccess() {
      registerBtnText.visibility = View.GONE
      validImage.apply {
         alpha = 0f
         visibility = View.VISIBLE
         animate()
               .alpha(1f)
               .setDuration(500)
               .setListener(object : AnimationEndListener() {
                  override fun onAnimationEnd(p0: Animator?) {
                     coordinatorManager.navigateInFeature(LOGIN)
                  }
               })
      }
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_register
   }

   override fun showContent(isVisible: Boolean) {
      //not used
   }

   override fun showError(isVisible: Boolean) {
      if (isVisible) errorText.visibility = View.VISIBLE else errorText.visibility = View.GONE
   }

   override fun showErrorResponse(error: String) {
      errorText.text = error
   }

   override fun showLoading(isVisible: Boolean) {
      //if (isVisible) registerBtn.startLoading() else registerBtn.stopLoading()
      if (isVisible) {
         registerBtnText.visibility = View.GONE
         registerBtnProgress.visibility = View.VISIBLE
      } else {
         registerBtnText.visibility = View.VISIBLE
         registerBtnProgress.visibility = View.GONE
      }
   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
   }

   private fun validateCredentials() {
      showError(false)
      presenter.validateRegistrationData(
            emailText.text.toString(),
            passwordText.text.toString(),
            retypePasswordText.text.toString()
      )
   }
}