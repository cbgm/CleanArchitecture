package com.distribution.christian.cleantest.cart.presentation.overview

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView
import com.distribution.christian.cleantest.cart.presentation.overview.model.UserOverviewEntity

interface OverviewContract {

   interface View : BaseView {
      fun showUsers(userOverviewEntity: UserOverviewEntity)
      fun showListLoading(isVisible: Boolean)
   }

   interface Presenter : BasePresenter<View> {
      fun loadUsers()
      fun loadMoreUsers()
   }
}