package com.distribution.christian.cleantest.event.presentation.overview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.distribution.christian.cleantest.core.core.ui.recycler.EndlessScrollListener
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.ui.EventBaseFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject


class OverviewFragment : EventBaseFragment(), OverviewContract.View, OverviewAdapter.OnItemClickListener {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment()
   }

   private val presenter: OverviewPresenter by inject()

   private var overviewAdapter: OverviewAdapter = OverviewAdapter(
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
      return R.layout.fragment_event_overview
   }

   override fun showEvents(eventOverviewEntity: EventOverviewEntity) {
      overviewAdapter.replaceData(eventOverviewEntity.events)
   }

   override fun showMoreEvents(eventOverviewEntity: EventOverviewEntity) {
      overviewAdapter.addData(eventOverviewEntity.events)
   }

   override fun showError(isVisible: Boolean) {
      //not needed
   }

   override fun showLoading(isVisible: Boolean) {
      if (isVisible) loading.startShimmer() else loading.stopShimmer()
   }

   override fun showListLoading(isVisible: Boolean) {
      if (isVisible) overviewAdapter.showLoading(true) else overviewAdapter.showLoading(false)
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) content.visibility = View.VISIBLE else content.visibility = View.GONE
   }

   override fun showUpdatedEventState(event: EventEntity) {
      overviewAdapter.updateItem(event)
   }

   override fun initViews(view: View) {
      content = view.findViewById(R.id.content)
      loading = view.findViewById(R.id.loading)
      userList = view.findViewById(R.id.user_list)
      userList.layoutManager = LinearLayoutManager(activity)
      userList.adapter = overviewAdapter
      userList.addOnScrollListener(object : EndlessScrollListener() {
         override fun onLoadMore() {
            presenter.loadMoreEvents()
         }
      })

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_overview,
            false
      )
   }

   override fun onItemClick(eventId: String, position: Int, sharedView: View) {
      cartFlowCoordinator.showDetail(
            eventId,
            TransitionInformation(sharedView, sharedView.transitionName)
      )
   }

   override fun onBookmarkClick(event: EventEntity) {
      presenter.updateEvent(event)
   }
}