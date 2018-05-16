package com.example.christian.cleantest.presentation.licenseview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.R
import com.example.christian.cleantest.presentation.licenseview.LicenseContract.View
import kotlinx.android.synthetic.main.activity_main.*

class LicenseActivity : AppCompatActivity(), View {
    override fun updateLicenses(licenseList: List<String>) {
        licenseList.forEach {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)
    }

    override fun showError(visible: Boolean) = Unit

    override fun showLoading(visible: Boolean) = Unit

    override fun showContent(visible: Boolean) {
        if (visible) content.visibility = android.view.View.VISIBLE else content.visibility = android.view.View.GONE
    }
}
