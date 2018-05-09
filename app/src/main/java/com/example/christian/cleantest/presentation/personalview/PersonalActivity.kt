package com.example.christian.cleantest.presentation.personalview

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.dagger.Injector
import com.example.christian.cleantest.core.ui.BaseActivity
import com.example.christian.cleantest.device.FragmentToolbar
import com.example.christian.cleantest.device.ToolbarLoader
import com.example.christian.cleantest.device.photo.PhotoCallbackObject
import com.example.christian.cleantest.device.photo.PhotoManager
import com.example.christian.cleantest.device.photo.RxPhotoBus
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_personal.*
import javax.inject.Inject

class PersonalActivity: BaseActivity(), PersonalContract.View {

    @Inject lateinit var presenter: PersonalPresenter
    @Inject lateinit var photoManager: PhotoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.initActivityComponent(this).inject(this)
        presenter.setVIew(this)
        initViews()
        ToolbarLoader(this, R.string.title_personal, false)
    }

    override fun getLayoutResId() = R.layout.activity_personal

    override fun toolbarBuilder(): FragmentToolbar {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun initPersonalisation(bitmap: Bitmap?) {
        bitmap?.let {
            personal_image.setImageBitmap(it)
            personal_image.visibility = View.VISIBLE
            personal_container.visibility = View.GONE
            return
        }
        personal_image.visibility = View.GONE
        personal_container.visibility = View.VISIBLE
    }

    override fun updatePersonalisation(bitmap: Bitmap?) {
        bitmap?.let {
            personal_image.setImageBitmap(it)
            personal_image.visibility = View.VISIBLE
            personal_container.visibility = View.GONE
            return
        }
        personal_image.visibility = View.GONE
        personal_container.visibility = View.VISIBLE
    }

    override fun showError(visible: Boolean) {
    }

    override fun showLoading(visible: Boolean) {
        if (visible) loading.visibility = View.VISIBLE else loading.visibility = View.GONE    }

    override fun showContent(visible: Boolean) {
        if (visible) content.visibility = View.VISIBLE else content.visibility = View.GONE
    }

    private fun initViews() {
        personalisation_btn.setOnClickListener {
            photoManager.initPicking("bla")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        RxPhotoBus.sendToBus(PhotoCallbackObject(requestCode, data))
    }
}