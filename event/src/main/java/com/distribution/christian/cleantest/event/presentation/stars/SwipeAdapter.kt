package com.distribution.christian.cleantest.event.presentation.stars

import android.view.LayoutInflater
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity


class SwipeAdapter(private val data: EventOverviewEntity, private var listener: SwipeAdapter.OnItemClickListener) : RecyclerView.Adapter<SwipeAdapter.EventViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_event, parent, false)
      return EventViewHolder(view)
   }

   override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
         val event = data.events[position]
         holder.nameText.text = event.name
         holder.cityText.text = event.city
         holder.locationText.text = event.location
         holder.dateText.text = event.date
         holder.starBtn.setImageResource(
               if (event.isStarred) {
                  R.drawable.ic_star
               } else {
                  R.drawable.ic_empty_star
               }
         )
         holder.starBtn.setOnClickListener {
            listener.onBookmarkClick(event)
         }
         ViewCompat.setTransitionName(holder.typeImg, "image$position")
   }

   override fun getItemCount(): Int = data.events.size

   fun addItem(event: EventEntity) {
      data.events.add(event)
      notifyItemInserted(data.events.size)
   }

   fun removeAt(position: Int) {
      data.events.removeAt(position)
      notifyItemRemoved(position)
   }

   interface OnItemClickListener {

      fun onItemClick(event: EventEntity, position: Int, sharedView: View)

      fun onBookmarkClick(event: EventEntity)
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