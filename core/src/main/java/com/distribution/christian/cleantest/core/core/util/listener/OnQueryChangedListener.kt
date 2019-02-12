package com.distribution.christian.cleantest.core.core.util.listener

import androidx.appcompat.widget.SearchView

open class OnQueryChangedListener: SearchView.OnQueryTextListener {
   override fun onQueryTextSubmit(query: String): Boolean {
      //not used
      return false
   }

   override fun onQueryTextChange(newText: String): Boolean {
      return false
   }
}