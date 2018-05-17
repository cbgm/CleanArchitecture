package com.example.christian.cleantest.presentation.licenseview

import com.example.christian.cleantest.core.ui.BasePresenter
import com.example.christian.cleantest.core.ui.BaseView
import com.example.christian.cleantest.presentation.licenseview.model.LicenseEntity

interface LicenseContract {

    interface View : BaseView {
        fun updateLicenses(licenseList: List<LicenseEntity>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadLicenses(carId: String)
    }
}