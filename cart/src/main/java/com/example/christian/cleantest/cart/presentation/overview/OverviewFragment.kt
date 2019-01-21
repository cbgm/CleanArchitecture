package com.example.christian.cleantest.cart.presentation.overview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.christian.cleantest.cart.R
import com.example.christian.cleantest.cart.core.ui.CartBaseFragment
import com.example.christian.cleantest.cart.presentation.overview.model.UserOverviewEntity
import com.example.christian.cleantest.core.core.ui.recycler.EndlessScrollListener
import com.example.christian.cleantest.core.core.util.extension.updateScope
import com.example.christian.cleantest.core.device.ToolbarLoader
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject


class OverviewFragment : CartBaseFragment(), OverviewContract.View, OverviewAdapter.OnItemClickListener {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment()
   }

   private val presenter: OverviewPresenter by inject()

   private var userAdapter: OverviewAdapter = OverviewAdapter(
         arrayListOf(),
         this
   )

   private lateinit var content: LinearLayout
   private lateinit var loading: ShimmerFrameLayout
   private lateinit var userList: RecyclerView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity?.updateScope("overview")
      presenter.setVIew(this)
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
      return R.layout.fragment_cart_overview
   }

   override fun showUsers(userOverviewEntity: UserOverviewEntity) {
      showLoading(false)
      userAdapter.addData(userOverviewEntity.users)
   }

   override fun showError(isVisible: Boolean) {
      //not needed
   }

   override fun showLoading(isVisible: Boolean) {
      if (isVisible) loading.startShimmer() else loading.stopShimmer()
   }

   override fun showListLoading(isVisible: Boolean) {
      if (isVisible) userAdapter.showLoading(true) else userAdapter.showLoading(false)
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) content.visibility = View.VISIBLE else content.visibility = View.GONE
   }

   override fun initViews(view: View) {
      content = view.findViewById(R.id.content)
      loading = view.findViewById(R.id.loading)
      userList = view.findViewById(R.id.user_list)
      userList.layoutManager = LinearLayoutManager(activity)
      userList.adapter = userAdapter
      userList.addOnScrollListener(object : EndlessScrollListener() {
         override fun onLoadMore() {
            presenter.loadMoreUsers()
         }
      })

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_overview,
            false
      )
   }

   override fun onItemClick(userId: String) {
      cartFlowCoordinator.showDetail(userId)
   }
}