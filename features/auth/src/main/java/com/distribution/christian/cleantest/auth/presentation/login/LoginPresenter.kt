package com.distribution.christian.cleantest.auth.presentation.login

import com.distribution.christian.cleantest.auth.domain.usecase.GetAuthenticatedUser
import com.distribution.christian.cleantest.auth.domain.usecase.LoginUser
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver


class LoginPresenter(
      private val loginUser: LoginUser,
      private val getAuthenticatedUser: GetAuthenticatedUser
) : LoginContract.Presenter {

   private lateinit var loginView: LoginContract.View

   private inner class LoginUserObserver : SingleLCEObserver<User>(loginView) {
      override fun onSuccess(value: User) {
         loginView.showLoginLoading(false)
         loginView.showLoginSuccess()
      }

      override fun onError(throwable: Throwable) {
         loginView.showLoginLoading(false)
      }
   }

   private inner class GetAuthenticatedUserObserver : SingleLCEObserver<User>(loginView) {
      override fun onSuccess(value: User) {
         loginView.showAlreadyAuthenticated()
      }

      override fun onError(throwable: Throwable) {
         loginView.showContent()
      }
   }

   override fun setVIew(view: LoginContract.View) {
      loginView = view
   }

   override fun onBind() {
      //not used
   }

   override fun checkLogin() {
      loginView.showLoading()
      getAuthenticatedUser.execute(GetAuthenticatedUserObserver(), Unit)
   }

   override fun validateLoginData(email: String, password: String) {
      if (email.isNotEmpty() && password.isNotEmpty()) {
         loginView.showEnabledLoginButton(true)
      } else {
         loginView.showEnabledLoginButton(false)
      }
   }

   override fun onUnbind() {
      getAuthenticatedUser.dispose()
      loginUser.dispose()
   }

   override fun login(email: String, password: String) {
      loginView.showLoginLoading(true)
      loginUser.executeWithTimeout(LoginUserObserver(), Pair(email, password), 10000)
   }
}