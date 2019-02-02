package com.distribution.christian.cleantest.login.presentation.login

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView


interface LoginContract {

   interface View: BaseView {

   }

   interface Presenter: BasePresenter<View> {

   }
}