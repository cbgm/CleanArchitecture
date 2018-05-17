package com.example.christian.cleantest.presentation.licenseview

import com.example.christian.cleantest.domain.model.License
import com.example.christian.cleantest.domain.usecases.GetLicensesByCar
import com.example.christian.cleantest.presentation.licenseview.mapper.LicenseDomainMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class LicensePresenter @Inject constructor(
        private val getLicensesByCar: GetLicensesByCar
) : LicenseContract.Presenter {
    lateinit var licenseView: LicenseContract.View

    inner class GetLicensesObserver : DisposableSingleObserver<List<License>>(){
        override fun onSuccess(t: List<License>) {
            licenseView.showContent(true)
            licenseView.showLoading(false)
            licenseView.showError(false)
            licenseView.updateLicenses(LicenseDomainMapper.transform(t))
        }

        override fun onError(e: Throwable) {
            licenseView.showError(true)
            licenseView.showLoading(false)
            licenseView.showContent(false)
        }

    }


    override fun loadLicenses() {
        getLicensesByCar.execute(GetLicensesObserver(), Unit)
    }

    override fun setView(v: LicenseContract.View) {
        licenseView = v
    }

    override fun onBind() {
        licenseView.showError(false)
        licenseView.showContent(false)
        licenseView.showLoading(true)
        loadLicenses()
    }

    override fun onUnbind() {
        getLicensesByCar.dispose()
    }

}