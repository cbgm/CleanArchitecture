package com.distribution.christian.cleantest.core.core.ui.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.R
import com.facebook.shimmer.ShimmerFrameLayout


abstract class InfiniteAdapter<I>(
      open var data: ArrayList<I>,
      val  shimmerPlaceholderId: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   companion object {
      private const val ITEM_VIEW_TYPE_CUSTOM = 0
      private const val ITEM_VIEW_TYPE_LOADING = 1
   }

   private var isLoading: Boolean = false
   lateinit var layoutInflater: LayoutInflater

   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

      if (holder is InfiniteAdapter<*>.ProgressViewHolder) {
         if (isLoading) holder.loading.startShimmer() else holder.loading.stopShimmer()

      } else {
         onBindCustomViewHolder(holder, position)
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      val viewHolder: RecyclerView.ViewHolder
      layoutInflater = LayoutInflater.from(parent.context)

      viewHolder = if (viewType == ITEM_VIEW_TYPE_LOADING) {

         val view = layoutInflater.inflate(R.layout.loading_list, parent, false)
         ProgressViewHolder(view)
      } else {
         onCreateCustomViewHolder(parent, viewType)
      }
      return viewHolder
   }

   override fun getItemCount(): Int {
      return if (data.size == 0) {
         0
      } else {
         data.size + 1
      }
   }

   override fun getItemViewType(position: Int): Int {

      return if (position != 0 && position == itemCount - 1)
         ITEM_VIEW_TYPE_LOADING
      else
         ITEM_VIEW_TYPE_CUSTOM
   }

   override fun getItemId(position: Int): Long {

      return if (position != 0 && position == itemCount - 1)
         -1
      else
         getCustomItemId(position)
   }

   fun replaceData(data: ArrayList<I>) {
      this.data = data
      notifyDataSetChanged()
   }

   fun addData(data: ArrayList<I>) {
      this.data.addAll(data)
      notifyDataSetChanged()
   }

   open fun showLoading(isVisible: Boolean) {
      isLoading = isVisible
   }

   abstract fun onCreateCustomViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

   abstract fun onBindCustomViewHolder(holder: RecyclerView.ViewHolder, position: Int)

   abstract fun getCustomItemId(position: Int): Long

   inner class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      var loading: ShimmerFrameLayout = view.findViewById(R.id.loading)

      init {
         val placeholderView = layoutInflater.inflate(shimmerPlaceholderId, view as ViewGroup, false)
         loading.addView(placeholderView)
      }

   }

}