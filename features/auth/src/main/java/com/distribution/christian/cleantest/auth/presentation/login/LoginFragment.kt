package com.distribution.christian.cleantest.auth.presentation.login

import android.animation.Animator
import android.os.Bundle
import android.text.Editable
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.ui.AuthBaseFragment
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.util.extension.getAndCreateScope
import com.distribution.christian.cleantest.core.core.util.listener.AnimationEndListener
import com.distribution.christian.cleantest.core.core.util.listener.NetworkListener
import com.distribution.christian.cleantest.core.core.util.listener.OnTextChangedListener
import com.distribution.christian.cleantest.core.core.util.network.NetworkReceiverManager
import org.koin.android.ext.android.inject
import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl.States.*
import com.distribution.christian.cleantest.core.core.util.extension.showStarsDialog


class LoginFragment : AuthBaseFragment(), LoginContract.View, NetworkListener {

   companion object {
      const val TAG = "Login"
      fun newInstance() = LoginFragment().apply {
         arguments = Bundle().apply {}
      }
   }

   private val presenter by lazy {
      val session = activity.getAndCreateScope(DiScope.AUTH_LOGIN)
      session.get<LoginPresenter>()
   }
   private val networkReceiverManager: NetworkReceiverManager by inject()


   private lateinit var content: View
   private lateinit var loading: View
   private lateinit var loginBtn: LinearLayout
   private lateinit var registerBtn: TextView
   private lateinit var resetBtn: TextView
   private lateinit var emailText: TextView
   private lateinit var passwordText: TextView
   private lateinit var showPasswordCheck: CheckBox
   private lateinit var loginBtnProgress: ProgressBar
   private lateinit var loginBtnText: TextView
   private lateinit var bookmarkBtn: ImageView
   private lateinit var validImage: ImageView

   private var loginWasChecked: Boolean = false

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      postponeEnterTransition()
      presenter.setVIew(this)
      loginWasChecked = arguments?.getBoolean("isUserLoggedIn", false)!!
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {

      val view = super.onCreateView(inflater, container, savedInstanceState)
      configureTransition(view)
      return view
   }

   override fun onResume() {
      super.onResume()
      presenter.onBind()
      networkReceiverManager.registerReceiver("login", this)
      if (!loginWasChecked) {
         loginWasChecked = true
         presenter.checkLogin()
      }
   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
      networkReceiverManager.unregisterReceiver("login")
   }

   override fun showError(isVisible: Boolean) {
      //not used
   }

   override fun showLoading(isVisible: Boolean) {
      if (isVisible) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
   }

   override fun showLoginLoading(isVisible: Boolean) {
      if (isVisible) {
         loginBtnText.visibility = View.GONE
         loginBtnProgress.visibility = View.VISIBLE
      } else {
         loginBtnText.visibility = View.VISIBLE
         loginBtnProgress.visibility = View.GONE
      }
   }

   override fun showAlreadyAuthenticated() {
      coordinatorManager.initialNavigateAppPart()
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) content.visibility = View.VISIBLE else content.visibility = View.GONE
   }

   override fun initViews(view: View) {
      content = view.findViewById(R.id.content)
      loading = view.findViewById(R.id.loading)
      loginBtn = view.findViewById(R.id.login_btn)
      registerBtn = view.findViewById(R.id.register_btn)
      resetBtn = view.findViewById(R.id.reset_btn)
      bookmarkBtn = view.findViewById(R.id.bookmark_btn)
      emailText = view.findViewById(R.id.email_text)
      passwordText = view.findViewById(R.id.password_text)
      showPasswordCheck = view.findViewById(R.id.show_password_check)
      loginBtnProgress = view.findViewById(R.id.login_loading)
      loginBtnText = view.findViewById(R.id.login_text)
      validImage = view.findViewById(R.id.valid_img)

      loginBtn.isEnabled = false

      showPasswordCheck.setOnCheckedChangeListener { _, isChecked ->
         if (isChecked) {
            passwordText.transformationMethod = HideReturnsTransformationMethod.getInstance()
         } else {
            passwordText.transformationMethod = PasswordTransformationMethod.getInstance()
         }
      }

      resetBtn.setOnClickListener {
         coordinatorManager.navigateInFeature(RESET)
      }

      bookmarkBtn.setOnClickListener {
         activity.showStarsDialog()
      }

      registerBtn.setOnClickListener {
         coordinatorManager.navigateInFeature(REGISTER)
      }

      loginBtn.setOnClickListener {
         presenter.login(emailText.text.toString(), passwordText.text.toString())
      }

      passwordText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            validateCredentials()
         }
      })


      emailText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            validateCredentials()
         }
      })
   }

   override fun showLoginSuccess() {
      loginBtnText.visibility = View.GONE
      validImage.apply {
         alpha = 0f
         visibility = View.VISIBLE
         animate()
               .alpha(1f)
               .setDuration(500)
               .setListener(object : AnimationEndListener() {
                  override fun onAnimationEnd(p0: Animator?) {
                     coordinatorManager.initialNavigateAppPart()
                  }
               })
      }

   }

   override fun showEnabledLoginButton(isEnabled: Boolean) {
      loginBtn.isEnabled = isEnabled
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_login
   }

   override fun onSaveInstanceState(outState: Bundle) {
      super.onSaveInstanceState(outState)
      arguments?.putBoolean("isUserLoggedIn", loginWasChecked)
   }

   override fun networkUnavailable() {
      //Toast.makeText(activity, "test", Toast.LENGTH_SHORT).show()
      content.visibility = View.INVISIBLE
   }

   override fun networkAvailable() {
      //Toast.makeText(activity, "test2", Toast.LENGTH_SHORT).show()
      content.visibility = View.VISIBLE
   }

   private fun configureTransition(view: View?) {
      view!!.findViewById<TextView>(R.id.header_text)
            .transitionName = "test"
      sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.default_transition)
      enterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.no_transition)
   }

   private fun validateCredentials() {
      showError(false)
      presenter.validateLoginData(
            emailText.text.toString(),
            passwordText.text.toString()
      )
   }
}