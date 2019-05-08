package com.distribution.christian.cleantest.auth.presentation.reset

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView


interface ResetContract {

   interface View: BaseView {
      fun showEnabledResetButton(isEnabled: Boolean)
      fun showResetSuccess()
   }

   interface Presenter: BasePresenter<View> {
      fun validateResetData(email: String)
   }
}