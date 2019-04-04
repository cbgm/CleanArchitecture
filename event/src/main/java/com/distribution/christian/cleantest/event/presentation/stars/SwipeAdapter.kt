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


class SwipeAdapter(private val data: EventOverviewEntity) : RecyclerView.Adapter<SwipeAdapter.EventViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_star, parent, false)
      return EventViewHolder(view)
   }

   override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
         val event = data.events[position]
         holder.nameText.text = event.name
         holder.cityText.text = event.city
         holder.locationText.text = event.location
         holder.descriptionText.text = event.description
         holder.daysLeftText.text = event.date
         ViewCompat.setTransitionName(holder.typeImg, "image$position")
   }

   override fun getItemCount(): Int = data.events.size

   fun removeAt(position: Int) {
      data.events.removeAt(position)
      notifyItemRemoved(position)
   }

   inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val nameText: TextView = view.findViewById(R.id.name_text)
      val daysLeftText: TextView = view.findViewById(R.id.days_left_text)
      val locationText: TextView = view.findViewById(R.id.location_text)
      val cityText: TextView = view.findViewById(R.id.city_text)
      val typeImg: ImageView = view.findViewById(R.id.type_img)
      val descriptionText: TextView = view.findViewById(R.id.description_text)

   }
}