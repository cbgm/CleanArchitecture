package com.distribution.christian.cleantest.event.presentation.overview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.ui.recycler.EndlessScrollListener
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.ui.EventBaseFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject


@Suppress("UNCHECKED_CAST")
class OverviewFragment : EventBaseFragment(), OverviewContract.View, OverviewAdapter.OnItemClickListener {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment().apply {
         arguments = Bundle().apply {
            putSerializable("Data", null)
         }
      }
   }

   private var data: ArrayList<EventEntity>? = null

   private val presenter: OverviewPresenter by inject()

   private var overviewAdapter: OverviewAdapter = OverviewAdapter(
         arrayListOf(),
         this
   )

   private lateinit var content: LinearLayout
   private lateinit var loading: ShimmerFrameLayout
   private lateinit var userList: RecyclerView

   private var posToReload: Int = -1

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.updateScope(DiScope.EVENT_OVERVIEW)
      presenter.setVIew(this)
      data = arguments?.getSerializable("Data")?.let { it as ArrayList<EventEntity> }
   }

   override fun onResume() {
      super.onResume()
      if (data == null) {
         presenter.onBind()
      } else {
         overviewAdapter.replaceData(data!!)
      }

      if(posToReload != -1){
         presenter.loadUpdatedEventById(data!![posToReload].id.toString())
         posToReload = -1
      }
   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_event_overview
   }

   override fun showEvents(eventOverviewEntity: EventOverviewEntity) {
      data = eventOverviewEntity.events
      overviewAdapter.replaceData(data!!)
   }

   override fun showMoreEvents(eventOverviewEntity: EventOverviewEntity) {
      data!!.addAll(eventOverviewEntity.events)
      overviewAdapter.addData(data!!)
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

   override fun onItemClick(event: EventEntity, position: Int, sharedView: View) {
      data = overviewAdapter.data
      posToReload = position
      activity.coordinator.showDetail(
            transitionInformation = TransitionInformation(sharedView, sharedView.transitionName),
            event = event
      )
   }

   override fun onBookmarkClick(event: EventEntity) {
      presenter.updateEvent(event)
   }

   override fun onSaveInstanceState(outState: Bundle) {
      super.onSaveInstanceState(outState)
      arguments?.putSerializable("Data", overviewAdapter.data)
   }
}