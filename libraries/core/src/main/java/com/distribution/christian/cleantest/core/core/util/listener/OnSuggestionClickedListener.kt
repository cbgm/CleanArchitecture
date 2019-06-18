package com.distribution.christian.cleantest.core.core.util.listener

import androidx.appcompat.widget.SearchView

open class OnSuggestionClickedListener: SearchView.OnSuggestionListener {
   override fun onSuggestionSelect(position: Int): Boolean {
      return true
   }

   override fun onSuggestionClick(position: Int): Boolean {
      //not used
      return true
   }

}