package com.example.christian.cleantest.presentation.licenseview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.dagger.Injector
import com.example.christian.cleantest.presentation.licenseview.LicenseContract.View
import com.example.christian.cleantest.presentation.licenseview.model.LicenseEntity
import kotlinx.android.synthetic.main.activity_license.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LicenseActivity : AppCompatActivity(), View {
    @Inject
    lateinit var licenseAdapter: LicenseAdapter

    override fun updateLicenses(licenseList: List<LicenseEntity>) {
        licenseAdapter.replaceData(arrayListOf(LicenseEntity("String")))
    }

    fun initViews() {
        license_list.layoutManager = LinearLayoutManager(this)
        license_list.adapter = licenseAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)
        Injector.initActivityComponent(this).inject(this)
        initViews()
    }

    override fun showError(visible: Boolean) = Unit

    override fun showLoading(visible: Boolean) = Unit

    override fun showContent(visible: Boolean) {
        if (visible) content.visibility = android.view.View.VISIBLE else content.visibility = android.view.View.GONE
    }
}
