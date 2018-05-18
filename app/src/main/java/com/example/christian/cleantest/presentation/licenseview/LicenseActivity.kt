package com.example.christian.cleantest.presentation.licenseview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.dagger.Injector
import com.example.christian.cleantest.core.ui.BaseActivity
import com.example.christian.cleantest.device.FragmentToolbar
import com.example.christian.cleantest.presentation.licenseview.LicenseContract.View
import com.example.christian.cleantest.presentation.licenseview.model.LicenseEntity
import kotlinx.android.synthetic.main.activity_license.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LicenseActivity : BaseActivity(), View {

    @Inject
    lateinit var licenseAdapter: LicenseAdapter

    @Inject
    lateinit var licensePresenter: LicensePresenter

    override fun getLayoutResId(): Int {
        return R.layout.activity_license
    }

    override fun toolbarBuilder(): FragmentToolbar {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun updateLicenses(licenseList: List<LicenseEntity>) {
        licenseAdapter.replaceData(arrayListOf(LicenseEntity("String")))
    }

    fun initViews() {
        license_list.layoutManager = LinearLayoutManager(this)
        license_list.adapter = licenseAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.initActivityComponent(this).inject(this)
        licensePresenter.setView(this)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        licensePresenter.onBind()
        licensePresenter.loadLicenses("123")
    }

    override fun onPause() {
        super.onPause()
        licensePresenter.onUnbind()
    }

    override fun showError(visible: Boolean) = Unit

    override fun showLoading(visible: Boolean) {
        if (visible) loading.visibility = android.view.View.VISIBLE else loading.visibility = android.view.View.GONE
    }

    override fun showContent(visible: Boolean) {
        if (visible) content.visibility = android.view.View.VISIBLE else content.visibility = android.view.View.GONE
    }
}
