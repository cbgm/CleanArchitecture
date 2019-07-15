package com.distribution.christian.cleantest.event.presentation.stars

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.util.extension.navigateGoogleMaps
import com.distribution.christian.cleantest.core.core.util.extension.getAndCreateScope
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.ui.EventBaseFragment
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity
import com.distribution.christian.cleantest.event.presentation.stars.model.EventStarsFragmentConsistency
import com.facebook.shimmer.ShimmerFrameLayout


class StarsFragment : EventBaseFragment<EventStarsFragmentConsistency>(), StarsContract.View, SwipeAdapter.OnClickListener {

   companion object {
      fun newInstance() = StarsFragment()
   }

   private val presenter by lazy {
      val session = activity.getAndCreateScope(DiScope.EVENT_STARS)
      session.get<StarsPresenter>()
   }
   private lateinit var simpleAdapter: SwipeAdapter

   private lateinit var starsList: RecyclerView
   private lateinit var content: LinearLayout
   private lateinit var error: LinearLayout
   private lateinit var loading: ShimmerFrameLayout

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
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
      return R.layout.fragment_event_stars
   }

   override fun showStars(eventOverviewEntity: EventOverviewEntity) {
      simpleAdapter = SwipeAdapter(eventOverviewEntity, this)
      starsList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
      starsList.layoutManager = LinearLayoutManager(activity)
      starsList.adapter = simpleAdapter
      val swipeHandler = object : SwipeCallback(activity) {
         override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val adapter = starsList.adapter as SwipeAdapter
            presenter.deleteEvent(eventOverviewEntity.events[viewHolder.adapterPosition])
            adapter.removeAt(viewHolder.adapterPosition)
         }
      }

      val itemTouchHelper = ItemTouchHelper(swipeHandler)
      itemTouchHelper.attachToRecyclerView(starsList)
   }

   override fun navigate(location: String) {
      activity.navigateGoogleMaps(location)
   }

   override fun initViews(view: View) {
      content = view.findViewById(R.id.content)
      loading = view.findViewById(R.id.loading)
      error = view.findViewById(R.id.error)
      starsList = view.findViewById(R.id.star_list)
   }

   override fun showContent(isVisible: Boolean) {
      content.visibility = if (isVisible) View.VISIBLE else View.GONE
   }

   override fun showError(isVisible: Boolean) {
      error.visibility = if (isVisible) View.VISIBLE else View.GONE
   }

   override fun showLoading(isVisible: Boolean) {
      loading.visibility = if (isVisible) View.VISIBLE else View.GONE
   }

   override fun showDeletedStars() {
      presenter.triggerEmptyEvents(starsList.adapter!!.itemCount)
   }
}