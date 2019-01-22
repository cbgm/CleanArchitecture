package com.distribution.christian.cleantest.profile.presentation.overview

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import android.view.MenuInflater
import android.view.MenuItem
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.core.ui.ProfileBaseFragment


class OverviewFragment : ProfileBaseFragment() {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_profile_overview
   }

   @SuppressLint("RestrictedApi")
   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      setHasOptionsMenu(true)
      return super.onCreateView(inflater, container, savedInstanceState)
   }

   override fun initViews(view: View) {
      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_profile,
            false
      )
   }

   override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
      //inflater!!.inflate(R.menu.toolbar_menu, menu)
      super.onCreateOptionsMenu(menu, inflater)
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {

      when(item!!.itemId) {
         //R.id.edit -> Toast.makeText(activity, "test", Toast.LENGTH_SHORT).show()
      }
      return super.onOptionsItemSelected(item)
   }
}