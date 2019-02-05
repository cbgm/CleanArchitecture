package com.distribution.christian.cleantest.auth.presentation.register

import com.distribution.christian.cleantest.auth.domain.usecase.RegisterNewUser
import com.distribution.christian.cleantest.core.domain.completable.CompletableLCEObserver


class RegisterPresenter(
      private val registerNewUSer: RegisterNewUser
) : RegisterContract.Presenter {

   private lateinit var registerView: RegisterContract.View

   private inner class PostNewUserObserver : CompletableLCEObserver(registerView) {
      override fun onComplete() {
         super.onComplete()
         registerView.showAddedUserSuccess()
      }

      override fun onError(throwable: Throwable) {
         super.onError(throwable)
         registerView.showErrorResponse(throwable.message!!)
      }
   }

   override fun addUser(email: String, password: String) {
      registerView.showLoading()
      registerNewUSer.execute(PostNewUserObserver(), Pair(email, password))
   }

   override fun validateRegistrationData(email: String, password: String, retypedPassword: String) {

      if (email.isNotEmpty() && password.isNotEmpty() && retypedPassword.isNotEmpty() && (password == retypedPassword)) {
         registerView.showEnabledRegisterButton(true)
      } else {
         registerView.showEnabledRegisterButton(false)
      }
   }

   override fun setVIew(view: RegisterContract.View) {
      this.registerView = view
   }

   override fun onBind() {
      //not used
   }

   override fun onUnbind() {
      registerNewUSer.dispose()
   }
}