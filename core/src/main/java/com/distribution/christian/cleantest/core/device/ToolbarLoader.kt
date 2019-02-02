package com.distribution.christian.cleantest.core.device

import android.support.annotation.StringRes
import android.support.design.widget.AppBarLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.distribution.christian.cleantest.core.R


class ToolbarLoader(
      private val activity: AppCompatActivity?,
      @StringRes
      private val title: Int,
      private val backButtonEnabled: Boolean
) {

   private val toolbar: ActionBar? = activity?.supportActionBar

   init {
      setTitle()
      setBackButton()
      resetAppBarScroll()
   }

   private fun resetAppBarScroll() {
      val appBarLayout = activity?.findViewById<AppBarLayout>(R.id.appBarLayout)
      appBarLayout?.setExpanded(true, false)
   }

   private fun setTitle() {

      if (this.title != -1) {
         this.toolbar?.setTitle(title)
      }
   }

   private fun setBackButton() {

      if (backButtonEnabled) {
         this.toolbar?.setDisplayShowHomeEnabled(false)
         this.toolbar?.setDisplayHomeAsUpEnabled(true)

         //custom back icon
         /*
         toolbar?.setHomeAsUpIndicator(
               ContextCompat.getDrawable(
                     activity!!.applicationContext,
                     android.support.v7.appcompat.R.drawable.abc_btn_check_material
               )
         )*/
      } else {
         this.toolbar?.setDisplayHomeAsUpEnabled(false)
      }
   }

}