package com.distribution.christian.cleantest.event.presentation.overview

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.core.core.ui.recycler.InfiniteAdapter
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import java.util.ArrayList

class OverviewAdapter(
      override var data: ArrayList<EventEntity>,
      private var listener: OnItemClickListener
) : InfiniteAdapter<EventEntity>(data, R.layout.shimmer_overview_item) {

   override fun onBindCustomViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

      if (holder is EventViewHolder) {
         val item = data[position]
         holder.nameText.text = item.name
         holder.cityText.text = item.city
         holder.locationText.text = item.location
         holder.dateText.text = item.date
         holder.starBtn.setOnClickListener { listener.onBookmarkClick(item.id.toString()) }
         ViewCompat.setTransitionName(holder.typeImg, "image$position")
         holder.itemView.setOnClickListener {
            listener.onItemClick(
                  item.id.toString(),
                  position,
                  holder.typeImg
            )
         }
      }
   }

   override fun onCreateCustomViewHolder(
         parent: ViewGroup,
         viewType: Int
   ): RecyclerView.ViewHolder {
      val view = layoutInflater.inflate(R.layout.list_item_event, parent, false)
      return EventViewHolder(view)
   }

   override fun getCustomItemId(position: Int): Long {
      return 1
   }

   interface OnItemClickListener {
      fun onItemClick(eventId: String, position: Int, sharedView: View)
      fun onBookmarkClick(eventId: String)
   }

   inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val nameText: TextView = view.findViewById(R.id.name_text)
      val dateText: TextView = view.findViewById(R.id.date_text)
      val locationText: TextView = view.findViewById(R.id.location_text)
      val cityText: TextView = view.findViewById(R.id.city_text)
      val starBtn: ImageView = view.findViewById(R.id.star_img)
      val typeImg: ImageView = view.findViewById(R.id.type_img)

   }
}