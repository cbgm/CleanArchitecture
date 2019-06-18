package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.distribution.christian.cleantest.core.R
import kotlinx.android.synthetic.main.toolbar.toolbar


abstract class BaseWindowActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_window)
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