package com.distribution.christian.cleantest.social.presentation.overview

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView


interface OverviewContract {

   interface View: BaseView {
      fun showOverview()
   }

   interface Presenter: BasePresenter<View> {
   }
}