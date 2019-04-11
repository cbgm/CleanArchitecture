package com.distribution.christian.cleantest.core.core.ui

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable


@Suppress("UNCHECKED_CAST")
class AutoCompleteAdapter(
      context: Context,
      textViewResourceId: Int,
      items: ArrayList<String>
) : ArrayAdapter<String>(context, textViewResourceId, items), Filterable {
   private val list: ArrayList<String> = ArrayList(items)
   private var suggestions: ArrayList<String> = ArrayList()

   override fun getFilter(): Filter {
      return object : Filter() {
         override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            if (constraint != null) {
               suggestions.clear()
               val tempList = ArrayList(list)
               suggestions.addAll(tempList.filter { it.contains(constraint) })
               filterResults.values = suggestions
               filterResults.count = suggestions.size
               return filterResults
            }
            return filterResults
         }

         override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val filterList = results?.values
            if (results != null && results.count > 0) {
               clear()
               addAll(filterList as MutableCollection<out String>)
               notifyDataSetChanged()
            }
         }
      }
   }
}