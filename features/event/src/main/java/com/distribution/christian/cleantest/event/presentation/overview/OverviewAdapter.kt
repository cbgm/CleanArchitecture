package com.distribution.christian.cleantest.event.presentation.overview

import android.annotation.SuppressLint
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.core.core.ui.recycler.InfiniteAdapter
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import java.util.ArrayList


@Suppress("UNCHECKED_CAST")
class OverviewAdapter(
      override var data: ArrayList<EventEntity>,
      private var listener: OnItemClickListener
) : InfiniteAdapter<EventEntity>(data, R.layout.shimmer_overview_item), Filterable {

   private lateinit var filteredData: ArrayList<EventEntity>

   override fun onBindCustomViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

      if (holder is EventViewHolder) {
         val event = data[position]
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
         holder.itemView.setOnClickListener {
            listener.onItemClick(
                  event,
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

   override fun getFilter(): Filter {
      return object : Filter() {
         @SuppressLint("DefaultLocale")
         override fun performFiltering(charSequence: CharSequence): FilterResults {
            val charString = charSequence.toString()
            filteredData = if (charString.isEmpty()) {
               data
            } else {
               data.filter { it.city.toLowerCase().contains(charString.toLowerCase()) } as ArrayList<EventEntity>
            }

            val filterResults = FilterResults()
            filterResults.values = filteredData
            return filterResults
         }

         override fun publishResults(
               charSequence: CharSequence,
               filterResults: FilterResults
         ) {
            data = filterResults.values as ArrayList<EventEntity>
            notifyDataSetChanged()
         }
      }
   }

   fun updateItem(event: EventEntity) {
      val position: Int = data.indexOf(data.first { it.id == event.id })
      data[position] = event
      notifyItemChanged(position)
   }

   fun updateItems(eventList: List<EventEntity>) {
      eventList.forEach{
        updateItem(it)
      }
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