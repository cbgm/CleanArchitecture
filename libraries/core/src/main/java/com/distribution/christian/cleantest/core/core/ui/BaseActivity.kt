package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import kotlinx.android.synthetic.main.toolbar.toolbar
import org.koin.android.ext.android.inject


abstract class BaseActivity<T: AppCompatActivity>(
      val layout: Int,
      private val withToolbar: Boolean = true
) : AppCompatActivity() {
   open lateinit var activeFeatureFragment: BaseFeatureFragment<T>
   private val splitInstallRequester: SplitInstallRequester by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(layout)

      if (withToolbar) {
         setSupportActionBar(toolbar)
      }
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {
      return when (item?.itemId) {
         android.R.id.home -> {
            onBackPressed()
            true
         }
         else -> return super.onOptionsItemSelected(item)
      }
   }
}