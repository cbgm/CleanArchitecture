package com.example.christian.cleantest.core.device

import android.support.annotation.StringRes
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

class ToolbarLoader(
      activity: AppCompatActivity?,
      @StringRes
      private val title: Int,
      private val backButtonEnabled: Boolean
) {

   val toolbar: ActionBar? = activity?.supportActionBar

   init {
      setTitle()
      setBackButton()
   }


   private fun setTitle() {
      if (this.title != -1) {
         toolbar?.setTitle(title)
      }
   }

   private fun setBackButton() {

      if (backButtonEnabled) {
         toolbar?.setDisplayShowHomeEnabled(false)
         toolbar?.setDisplayHomeAsUpEnabled(true)

         //custom back icon
         /*
         toolbar?.setHomeAsUpIndicator(
               ContextCompat.getDrawable(
                     activity!!.applicationContext,
                     android.support.v7.appcompat.R.drawable.abc_btn_check_material
               )
         )*/
      } else {
         toolbar?.setDisplayHomeAsUpEnabled(false)
      }
   }

}