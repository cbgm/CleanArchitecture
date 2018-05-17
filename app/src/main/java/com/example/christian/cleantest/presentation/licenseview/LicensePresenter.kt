package com.example.christian.cleantest.presentation.licenseview

class LicensePresenter : LicenseContract.Presenter {
    lateinit var licenseView: LicenseContract.View
    override fun loadLicenses() {
        //TODO
    }

    override fun setView(v: LicenseContract.View) {
        licenseView = v
    }

    override fun onBind() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUnbind() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}