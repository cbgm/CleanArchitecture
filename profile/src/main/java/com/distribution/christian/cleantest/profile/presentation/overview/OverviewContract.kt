package com.distribution.christian.cleantest.profile.presentation.overview

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView
import com.distribution.christian.cleantest.profile.presentation.overview.model.ProfileOverviewEntity

interface OverviewContract {

   interface View: BaseView {
      fun showProfile(profileOverviewEntity: ProfileOverviewEntity)
   }

   interface Presenter: BasePresenter<View> {
      fun loadProfile()
      fun updateProfile(profileOverviewEntity: ProfileOverviewEntity)
   }
}