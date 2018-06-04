package com.example.christian.cleantest.presentation.licenseview

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.dagger.Injector
import com.example.christian.cleantest.core.ui.BaseActivity
import com.example.christian.cleantest.core.ui.HintDialog
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.device.FragmentToolbar
import com.example.christian.cleantest.device.photo.PhotoCallbackObject
import com.example.christian.cleantest.device.photo.PhotoManager
import com.example.christian.cleantest.device.photo.RxPhotoBus
import com.example.christian.cleantest.presentation.licenseview.model.LicenseEntity
import kotlinx.android.synthetic.main.activity_license.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LicenseActivity : BaseActivity(), LicenseContract.View, PhotoManager.PhotoManagerCallback {

    @Inject
    lateinit var licenseAdapter: LicenseAdapter

    @Inject
    lateinit var photoManager: PhotoManager

    @Inject
    lateinit var imageUtil: ImageUtil

    @Inject
    lateinit var licensePresenter: LicensePresenter

    override fun getLayoutResId(): Int {
        return R.layout.activity_license
    }

    override fun toolbarBuilder(): FragmentToolbar {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun updateLicenses(licenseList: List<LicenseEntity>) {
        if (licenseList.isNotEmpty()) {
            license_list.visibility = View.VISIBLE
            linearLayout.visibility = View.GONE
            licenseAdapter.replaceData(licenseList)
            license_list.adapter = this.licenseAdapter
        } else {
            license_list.visibility = View.GONE
            linearLayout.visibility = View.VISIBLE
        }

    }

    private fun initViews() {
        license_list.layoutManager = LinearLayoutManager(this)
        license_list.adapter = licenseAdapter
        photoManager.setPhotoManagerCallback(this)
        add_license_btn.setOnClickListener {
            imageUtil.fileName = imageUtil.getValidFileName()
            photoManager.showPhotoOptions(false)
        }
        usage_license_btn.setOnClickListener {
            HintDialog(this, getString(R.string.car_license_text), getString(R.string.car_license_usage_text)).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.initActivityComponent(this).inject(this)
        imageUtil.fileName = imageUtil.getValidFileName()
        imageUtil.setLicensePath("123")
        imageUtil.createLicensesPath()
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
        if (visible) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
    }

    override fun showContent(visible: Boolean) {
        if (visible) content.visibility = View.VISIBLE else content.visibility = View.GONE
    }

    override fun imageReady() {
//        licenseAdapter.replaceData(licenseAdapter.list)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //TODO refactor exact line in PersonalActivity.class
        if (!(requestCode == 1 && resultCode == Activity.RESULT_CANCELED) && !(requestCode == 4 && resultCode == Activity.RESULT_CANCELED))
            RxPhotoBus.sendToBus(PhotoCallbackObject(requestCode, data))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PhotoManager.WRITE_EXTERNAL_STORAGE_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            photoManager.cameraPermissionsGranted()
        }
    }

}
