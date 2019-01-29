package com.distribution.christian.cleantest.profile.presentation.overview

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView

interface OverviewContract {

   interface View: BaseView {
      fun showProfile()
   }

   interface Presenter: BasePresenter<View> {
      fun loadProfile()
   }
}