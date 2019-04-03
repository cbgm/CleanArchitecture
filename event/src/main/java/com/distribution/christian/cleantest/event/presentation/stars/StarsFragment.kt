package com.distribution.christian.cleantest.event.presentation.stars

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.ui.EventBaseFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity
import com.distribution.christian.cleantest.event.presentation.stars.model.EventStarsFragmentConsistency
import kotlinx.android.synthetic.main.fragment_event_stars.recyle
import org.koin.android.ext.android.inject

class StarsFragment: EventBaseFragment<EventStarsFragmentConsistency>(), StarsContract.View, SwipeAdapter.OnItemClickListener {

   companion object {

      const val TAG = "Stars"
      fun newInstance() = StarsFragment()
   }

   private val presenter: StarsPresenter by inject()
   private lateinit var simpleAdapter:SwipeAdapter

   private lateinit var starsList: RecyclerView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.updateScope(DiScope.EVENT_STARS)
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

   override fun onItemClick(event: EventEntity, position: Int, sharedView: View) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun onBookmarkClick(event: EventEntity) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
      itemTouchHelper.attachToRecyclerView(recyle)
   }
   override fun initViews(view: View) {
      //content = view.findViewById(R.id.content)
      //loading = view.findViewById(R.id.loading)
      starsList = view.findViewById(R.id.recyle)

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.empty,
            false
      )
   }

   override fun showContent(isVisible: Boolean) {
   }

   override fun showError(isVisible: Boolean) {
   }

   override fun showLoading(isVisible: Boolean) {
   }
}