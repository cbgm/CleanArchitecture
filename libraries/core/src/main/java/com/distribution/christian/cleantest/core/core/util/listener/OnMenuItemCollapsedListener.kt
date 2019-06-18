package com.distribution.christian.cleantest.core.core.util.listener

import android.view.MenuItem

open class OnMenuItemCollapsedListener: MenuItem.OnActionExpandListener {

   override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
      //no used
      return true
   }

   override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
      return true
   }
}