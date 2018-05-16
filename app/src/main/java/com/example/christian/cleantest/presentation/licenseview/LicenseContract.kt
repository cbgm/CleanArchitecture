package com.example.christian.cleantest.presentation.licenseview

import com.example.christian.cleantest.core.ui.BasePresenter
import com.example.christian.cleantest.core.ui.BaseView

interface LicenseContract {

    interface View : BaseView {
        fun updateLicenses(licenseList: List<String>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadLicenses()
    }
}