package com.distribution.christian.cleantest.shop.presentation.overview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.shop.core.ui.ShopBaseFragment
import com.example.christian.cleantest.shop.R


class OverviewFragment : ShopBaseFragment() {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment()
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setHasOptionsMenu(true)
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_shop_overview
   }

   override fun initViews(view: View) {
      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_shop,
            false
      )
   }

   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
      inflater.inflate(R.menu.toolbar_menu, menu)
      super.onCreateOptionsMenu(menu, inflater)
   }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {

      when(item.itemId) {
         R.id.edit -> Toast.makeText(activity, "test", Toast.LENGTH_SHORT).show()
      }
      return super.onOptionsItemSelected(item)
   }
}