package com.example.christian.cleantest.cart.presentation.overview

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.christian.cleantest.cart.R
import com.example.christian.cleantest.cart.core.ui.BaseActivity
import com.example.christian.cleantest.core.device.ToolbarLoader
import com.example.christian.cleantest.cart.presentation.cartview.CartActivity
import com.example.christian.cleantest.cart.presentation.overview.model.UserOverviewEntity
import kotlinx.android.synthetic.main.activity_main.content
import kotlinx.android.synthetic.main.activity_main.loading
import kotlinx.android.synthetic.main.activity_overview.user_list
import org.koin.android.ext.android.inject


class OverviewActivity : BaseActivity(), OverviewContract.View, OverviewAdapter.OnItemClickListener {

   private val presenter: OverviewPresenter by inject()

   private var userAdapter: OverviewAdapter = OverviewAdapter(
         arrayListOf(),
         this
   )

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      presenter.setVIew(this)
      initViews()
      ToolbarLoader(
            this,
            R.string.title_overview,
            false
      )
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
      val intent = Intent(this, CartActivity::class.java)
      intent.putExtra("User", userId)
      startActivityForResult(intent, 10)
   }
}