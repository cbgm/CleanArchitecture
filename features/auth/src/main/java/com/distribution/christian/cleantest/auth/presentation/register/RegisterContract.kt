package com.distribution.christian.cleantest.auth.presentation.register

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView


interface RegisterContract {

   interface View: BaseView {

      fun showAddedUserSuccess()

      fun showEnabledRegisterButton(isEnabled: Boolean)

      fun showAddedUserFailure()

      fun showErrorResponse(error: String)
   }

   interface Presenter: BasePresenter<View> {

      fun addUser(email: String, password: String)

      fun validateRegistrationData(email: String, password: String, retypedPassword: String)
   }
}