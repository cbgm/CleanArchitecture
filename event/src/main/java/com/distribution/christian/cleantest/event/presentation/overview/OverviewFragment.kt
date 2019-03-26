package com.distribution.christian.cleantest.event.presentation.overview

import android.annotation.SuppressLint
import android.content.Context
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.ui.recycler.EndlessScrollListener
import com.distribution.christian.cleantest.core.core.util.extension.args
import com.distribution.christian.cleantest.core.core.util.extension.argsUpdate
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.ui.EventBaseFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewFragmentConsistency
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject
import android.widget.EditText
import android.database.Cursor
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.distribution.christian.cleantest.core.core.util.extension.navigateToStars
import com.distribution.christian.cleantest.core.core.util.listener.OnMenuItemCollapsedListener
import com.distribution.christian.cleantest.core.core.util.listener.OnQueryChangedListener
import com.distribution.christian.cleantest.core.core.util.listener.OnSuggestionClickedListener
import com.distribution.christian.cleantest.core.presentation.model.SearchEntity


@Suppress("UNCHECKED_CAST")
class OverviewFragment : EventBaseFragment<EventOverviewFragmentConsistency>(), OverviewContract.View, OverviewAdapter.OnItemClickListener {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment().args {
         putSerializable(EventOverviewFragmentConsistency.DATA_KEY, null)
         putInt(EventOverviewFragmentConsistency.POS_KEY, -1)
      }
   }

   private val presenter: OverviewPresenter by inject()

   private var overviewAdapter: OverviewAdapter = OverviewAdapter(
         arrayListOf(),
         this
   )

   private lateinit var content: LinearLayout
   private lateinit var loading: ShimmerFrameLayout
   private lateinit var userList: RecyclerView
   private lateinit var searchView: SearchView
   private lateinit var searchItem: MenuItem
   private lateinit var cityAdapter: SimpleCursorAdapter
   private lateinit var swipeContainer: SwipeRefreshLayout
   private var searchEntity: SearchEntity? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.updateScope(DiScope.EVENT_OVERVIEW)
      presenter.setVIew(this)
      setHasOptionsMenu(true)
      initCitySuggestionAdapter()
   }

   @SuppressLint("CheckResult")
   override fun onAttach(context: Context?) {
      super.onAttach(context)
      consistency = EventOverviewFragmentConsistency.deserializeFrom(this)
      consistency.searchTerm.observable.subscribe {
         if (it != "") {
            searchEntity = SearchEntity("", it, "", 0, 0)
         } else {
            searchEntity = null
         }
      }
   }

   override fun onResume() {
      super.onResume()
      if (consistency.data == null) {
         presenter.onBind()
      } else {
         overviewAdapter.replaceData(consistency.data!!)
      }

      if (consistency.posToReload != -1) {
         presenter.loadUpdatedEventById(consistency.data!![consistency.posToReload].id.toString())
         consistency.posToReload = -1
      }
   }

   @SuppressLint("ResourceType")
   override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
      inflater!!.inflate(R.menu.event_toolbar_menu, menu)
      super.onCreateOptionsMenu(menu, inflater)
   }

   override fun onPrepareOptionsMenu(menu: Menu?) {
      super.onPrepareOptionsMenu(menu)
      searchItem = menu!!.findItem(R.id.search)
      searchItem.setOnActionExpandListener(object : OnMenuItemCollapsedListener() {

         override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
            consistency.searchTerm.value = ""
            presenter.loadEvents(searchEntity)
            return super.onMenuItemActionCollapse(menuItem)
         }
      })
      searchView = searchItem.actionView as SearchView
      initSearch()
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {

      when (item!!.itemId) {
         R.id.search -> Toast.makeText(activity, "test", Toast.LENGTH_SHORT).show()
         R.id.stars -> activity.coordinator.showStars()
      }
      return super.onOptionsItemSelected(item)
   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_event_overview
   }

   override fun showEvents(eventOverviewEntity: EventOverviewEntity) {
      consistency.data = eventOverviewEntity.events
      overviewAdapter.replaceData(consistency.data!!)
   }

   override fun showMoreEvents(eventOverviewEntity: EventOverviewEntity) {
      consistency.data!!.addAll(eventOverviewEntity.events)
      overviewAdapter.addData(consistency.data!!)
   }

   override fun showError(isVisible: Boolean) {
      //not needed
   }

   override fun showLoading(isVisible: Boolean) {
      if (isVisible) {
         loading.startShimmer()
      } else {
         loading.stopShimmer()
         swipeContainer.isRefreshing = false
      }
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
      swipeContainer = view.findViewById(R.id.swipeContainer)
      userList = view.findViewById(R.id.user_list)
      userList.layoutManager = LinearLayoutManager(activity)
      userList.adapter = overviewAdapter
      userList.addOnScrollListener(object : EndlessScrollListener() {
         override fun onLoadMore() {
            presenter.loadMoreEvents(searchEntity)
         }
      })

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_overview,
            false
      )

      swipeContainer.setOnRefreshListener {
         presenter.loadEvents(searchEntity)
      }
   }

   override fun onItemClick(event: EventEntity, position: Int, sharedView: View) {
      consistency.data = overviewAdapter.data
      consistency.posToReload = position
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
      argsUpdate {
         putSerializable(EventOverviewFragmentConsistency.DATA_KEY, consistency.data)
         putInt(EventOverviewFragmentConsistency.POS_KEY, consistency.posToReload)
         putString(
               EventOverviewFragmentConsistency.SEARCH_TERM_KEY, consistency.searchTerm.value
         )
      }
   }

   override fun showPossibleCitys(citys: Array<String>) {
      val c = MatrixCursor(arrayOf(BaseColumns._ID, "cityName"))
      for (i in 0 until citys.size) {
         c.addRow(arrayOf(i, citys[i]))
      }
      cityAdapter.changeCursor(c)
   }

   private fun initSearch() {
      searchView.suggestionsAdapter = cityAdapter
      val searchText = searchView.findViewById(R.id.search_src_text) as EditText
      val closeButton = searchView.findViewById<ImageView>(R.id.search_close_btn)

      closeButton.setOnClickListener {
         searchText.setText("")
         consistency.searchTerm.value = ""
         searchView.setQuery("", false)
         searchView.onActionViewCollapsed()
         searchItem.collapseActionView()
         presenter.loadEvents(searchEntity)
      }

      searchView.setOnSuggestionListener(object : OnSuggestionClickedListener() {
         override fun onSuggestionClick(position: Int): Boolean {
            val cursor = cityAdapter.getItem(position) as Cursor
            consistency.searchTerm.value = cursor.getString(1)
            searchText.setText(consistency.searchTerm.value)
            searchView.clearFocus()
            //overviewAdapter.filter.filter(consistency.searchTerm)
            presenter.loadEvents(searchEntity)
            return true
         }
      })
      searchView.setOnQueryTextListener(object : OnQueryChangedListener() {

         override fun onQueryTextChange(newText: String): Boolean {
            consistency.searchTerm.value = newText
            presenter.loadCitySuggestions(newText)
            return false
         }
      })
   }

   private fun initCitySuggestionAdapter() {
      val from = arrayOf("cityName")
      val to = intArrayOf(android.R.id.text1)
      cityAdapter = SimpleCursorAdapter(
            activity,
            android.R.layout.simple_list_item_1,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
      )
   }
}


