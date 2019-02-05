package com.distribution.christian.cleantest.auth.presentation.login

import com.distribution.christian.cleantest.auth.domain.usecase.GetAuthenticatedUser
import com.distribution.christian.cleantest.auth.domain.usecase.LoginUser
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver


class LoginPresenter(
      private val loginUser: LoginUser,
      private val getAuthenticatedUser: GetAuthenticatedUser
): LoginContract.Presenter {

   private lateinit var loginView: LoginContract.View

   private inner class LoginUserObserver: SingleLCEObserver<User>(loginView) {
      override fun onSuccess(value: User) {
         super.onSuccess(value)
         loginView.showLoginSuccess()
      }
   }

   private inner class GetAuthenticatedUserObserver: SingleLCEObserver<User>(loginView) {
      override fun onSuccess(value: User) {
         loginView.showAlreadyAuthenticated()
      }
   }

   override fun setVIew(view: LoginContract.View) {
      loginView = view
   }

   override fun onBind() {
      getAuthenticatedUser.execute(GetAuthenticatedUserObserver(), Unit)
   }

   override fun onUnbind() {
      loginUser.dispose()
   }

   override fun login(email: String, password: String) {
      loginView.showLoading()
      loginUser.execute(LoginUserObserver(), Pair(email, password))
   }
}