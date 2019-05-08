package com.distribution.christian.cleantest.auth.presentation.reset


class ResetPresenter: ResetContract.Presenter {

   private lateinit var resetView: ResetContract.View

   override fun setVIew(view: ResetContract.View) {
      this.resetView = view
   }

   override fun onBind() {
      //not used
   }

   override fun onUnbind() {
      //not used
   }

   override fun validateResetData(email: String) {
      if (email.isNotEmpty()) {
         resetView.showEnabledResetButton(true)
      } else {
         resetView.showEnabledResetButton(false)
      }
   }
}