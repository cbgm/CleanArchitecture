package com.distribution.christian.cleantest.auth.presentation.login

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView


interface LoginContract {

   interface View: BaseView {
      fun showLoginSuccess()
      fun showAlreadyAuthenticated()
      fun showLoginLoading(isVisible: Boolean)
   }

   interface Presenter: BasePresenter<View> {
      fun login(email: String, password: String)
      fun checkLogin()
   }
}