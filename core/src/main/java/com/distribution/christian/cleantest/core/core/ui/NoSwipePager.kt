package com.distribution.christian.cleantest.core.core.ui

import android.content.Context
import android.view.MotionEvent
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager


class NoSwipePager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
   private var swipeEnabled: Boolean = true

   override fun onTouchEvent(event: MotionEvent): Boolean {
      return if (this.swipeEnabled) {
         super.onTouchEvent(event)
      } else false
   }

   override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
      return if (this.swipeEnabled) {
         super.onInterceptTouchEvent(event)
      } else false
   }

   fun setPagingEnabled(enabled: Boolean) {
      this.swipeEnabled = enabled
   }
}