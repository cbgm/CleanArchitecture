package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.distribution.christian.cleantest.core.R
import kotlinx.android.synthetic.main.toolbar.toolbar


abstract class BaseWindowActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
      setContentView(R.layout.activity_window)
      window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
      setSupportActionBar(toolbar)

   }

   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      val inflater = menuInflater
      inflater.inflate(R.menu.window_menu, menu)
      return true
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {
      return when (item?.itemId) {
         R.id.close -> {
            finish()
            true
         }
         else -> return super.onOptionsItemSelected(item)
      }
   }
}