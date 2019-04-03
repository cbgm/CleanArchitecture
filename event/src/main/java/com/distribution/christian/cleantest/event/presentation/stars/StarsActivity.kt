package com.distribution.christian.cleantest.event.presentation.stars

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.data.cache.EventCache
import com.distribution.christian.cleantest.event.data.mapper.EventDtoMapper
import com.distribution.christian.cleantest.event.presentation.detail.mapper.EventDomainMapper
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity
import kotlinx.android.synthetic.main.activity_stars.button2
import kotlinx.android.synthetic.main.activity_stars.recyle
import org.koin.android.ext.android.inject


class StarsActivity : BaseActivity(R.layout.activity_stars),StarsContract.View, SwipeAdapter.OnItemClickListener {
   private val presenter: StarsPresenter by inject()
   private lateinit var simpleAdapter:SwipeAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      this.updateScope(DiScope.EVENT_STARS)
      presenter.setVIew(this)
   }

   override fun onResume() {
      super.onResume()
      presenter.onBind()
      button2.setOnClickListener {
         this.finish()
      }
   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
   }

   override fun finish() {
      super.finish()
      presenter.onUnbind()
   }

   override fun onItemClick(event: EventEntity, position: Int, sharedView: View) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun onBookmarkClick(event: EventEntity) {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override fun showStars(eventOverviewEntity: EventOverviewEntity) {
      simpleAdapter = SwipeAdapter(eventOverviewEntity, this)
      recyle.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
      recyle.layoutManager = LinearLayoutManager(this)
      recyle.adapter = simpleAdapter
      val swipeHandler = object : SwipeCallback(this) {
         override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val adapter = recyle.adapter as SwipeAdapter
            presenter.deleteEvent(eventOverviewEntity.events[viewHolder.adapterPosition])
            adapter.removeAt(viewHolder.adapterPosition)
         }
      }

      val itemTouchHelper = ItemTouchHelper(swipeHandler)
      itemTouchHelper.attachToRecyclerView(recyle)
   }

   override fun showContent(isVisible: Boolean) {
   }

   override fun showError(isVisible: Boolean) {
   }

   override fun showLoading(isVisible: Boolean) {
   }
}
