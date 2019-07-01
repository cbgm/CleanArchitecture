package com.distribution.christian.cleantest.auth.presentation.reset

import android.animation.Animator
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.ui.AuthBaseFragment
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.core.util.listener.AnimationEndListener
import com.distribution.christian.cleantest.core.core.util.listener.OnTextChangedListener
import org.koin.android.ext.android.inject


class ResetFragment : AuthBaseFragment(), ResetContract.View {

   companion object {
      const val TAG = "Reset"
      fun newInstance() = ResetFragment()
   }

   private lateinit var resetBtn: LinearLayout
   private lateinit var resetBtnProgress: ProgressBar
   private lateinit var resetBtnText: TextView
   private lateinit var emailText: TextView
   private lateinit var validImage: ImageView
   private lateinit var backBtn: TextView

   private val presenter: ResetPresenter by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.updateScope(DiScope.AUTH_RESET)
      presenter.setVIew(this)
   }

   override fun onResume() {
      super.onResume()
      presenter.onBind()
   }

   override fun initViews(view: View) {
      resetBtn = view.findViewById(R.id.reset_btn)
      resetBtnProgress = view.findViewById(R.id.reset_loading)
      resetBtnText = view.findViewById(R.id.reset_text)
      validImage = view.findViewById(R.id.valid_img)
      emailText = view.findViewById(R.id.email_text)
      backBtn = view.findViewById(R.id.back_btn)
      backBtn.setOnClickListener {
         coordinatorManager.backStackFeature()
      }
      emailText.addTextChangedListener(object : OnTextChangedListener() {
         override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            validateEmail()
         }
      })
      resetBtn.isEnabled = false
   }

   override fun showEnabledResetButton(isEnabled: Boolean) {
      resetBtn.isEnabled = isEnabled
   }

   override fun showResetSuccess() {
      resetBtnText.visibility = View.GONE
      validImage.apply {
         alpha = 0f
         visibility = View.VISIBLE
         animate()
               .alpha(1f)
               .setDuration(500)
               .setListener(object : AnimationEndListener() {
                  override fun onAnimationEnd(p0: Animator?) {
                     coordinatorManager.currentFeatureCoordinator.initialNavigation()
                  }
               })
      }
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_reset
   }

   override fun showError(isVisible: Boolean) {
      //not used
   }

   override fun showLoading(isVisible: Boolean) {
      //not used
   }

   override fun showContent(isVisible: Boolean) {
      //not used
   }

   private fun validateEmail() {
      showError(false)
      presenter.validateResetData(
            emailText.text.toString()
      )
   }
}