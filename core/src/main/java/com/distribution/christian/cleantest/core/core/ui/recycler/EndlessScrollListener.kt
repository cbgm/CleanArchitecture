package com.distribution.christian.cleantest.core.core.ui.recycler

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessScrollListener : RecyclerView.OnScrollListener() {

   private var loading: Boolean = true
   private var previousTotal: Int = 0

   override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
      super.onScrolled(recyclerView, dx, dy)

      recyclerView.let {
         val visibleCount = it.childCount
         val totalCount = it.layoutManager!!.itemCount
         val firstVisibleItem = (it.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

         if (loading && totalCount > previousTotal) {
            loading = false
            previousTotal = totalCount
         }
         val visibleThreshold = 5
         if (!loading && (totalCount - visibleCount) <= (firstVisibleItem + visibleThreshold)) {
            onLoadMore()
            loading = true
         }


      }
   }

   abstract fun onLoadMore()
}