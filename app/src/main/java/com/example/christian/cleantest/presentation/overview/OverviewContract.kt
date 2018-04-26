package com.example.christian.cleantest.presentation.overview

import com.example.christian.cleantest.core.ui.BasePresenter
import com.example.christian.cleantest.core.ui.BaseView
import com.example.christian.cleantest.presentation.overview.model.UserOverviewEntity

interface OverviewContract {

    interface View: BaseView {
        fun updateUsers(userOverviewEntity: UserOverviewEntity)
        fun initUsers(userOverviewEntity: UserOverviewEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun loadUsers()
    }
}