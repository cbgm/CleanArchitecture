package com.example.christian.cleantest.presentation.overview

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.dagger.Injector
import com.example.christian.cleantest.core.ui.BaseActivity
import com.example.christian.cleantest.device.FragmentToolbar
import com.example.christian.cleantest.device.ToolbarLoader
import com.example.christian.cleantest.presentation.overview.model.UserOverviewEntity
import com.example.christian.cleantest.presentation.personalview.PersonalActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_overview.*
import javax.inject.Inject


class OverviewActivity : BaseActivity(), OverviewContract.View, OverviewAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: OverviewPresenter

    private var userAdapter: OverviewAdapter = OverviewAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //component.inject(this)
        Injector.initActivityComponent(this).inject(this)
        presenter.setView(this)
        initViews()
        ToolbarLoader(this, R.string.title_overview, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.onBind()
    }

    override fun onPause() {
        super.onPause()
        presenter.onUnbind()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_overview
    }

    override fun toolbarBuilder(): FragmentToolbar {
        return FragmentToolbar.Builder()
                .withBarReference(supportActionBar!!)
                .withTitle(R.string.title_overview)
                .withBackButton(true)
                .build()
    }


    override fun updateUsers(userOverviewEntity: UserOverviewEntity) {
        showLoading(false)
        userAdapter.replaceData(userOverviewEntity.users)
    }

    override fun initUsers(userOverviewEntity: UserOverviewEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(visible: Boolean) {
        //if (visible) error.visibility = View.VISIBLE else error.visibility = View.GONE
    }

    override fun showLoading(visible: Boolean) {
        if (visible) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
    }

    override fun showContent(visible: Boolean) {
        if (visible) content.visibility = View.VISIBLE else content.visibility = View.GONE
    }

    private fun initViews() {
        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = userAdapter
    }

    override fun onItemClick(userId: String) {
        /*val intent = Intent(this, CartActivity::class.java)
        intent.putExtra("User", userId)
        startActivityForResult(intent, 10)*/
        val intent = Intent(this, PersonalActivity::class.java)
       intent.putExtra("User", userId)
       startActivityForResult(intent, 11)
    }
}