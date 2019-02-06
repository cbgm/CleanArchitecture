package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.distribution.christian.cleantest.core.R
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinator
import com.distribution.christian.cleantest.core.core.util.extension.navigateToEvents
import com.distribution.christian.cleantest.core.core.util.extension.navigateToProfile
import com.distribution.christian.cleantest.core.core.util.extension.navigateToShop
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import kotlinx.android.synthetic.main.toolbar.toolbar
import org.koin.android.ext.android.inject


abstract class BaseActivity(private val layout: Int? = null) : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
   open val coordinator: BaseCoordinator? = null
   lateinit var bottomNavigationView: BottomNavigationView
   private val splitInstallRequester: SplitInstallRequester by inject()


   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      if (layout == null) {
         setContentView(R.layout.activity_main)
         initBottomNavigation()
         setSupportActionBar(toolbar)
      } else {
         setContentView(layout)
      }

      /*supportFragmentManager.addOnBackStackChangedListener {
         val currentFragment = supportFragmentManager
               .findFragmentById(R.id.fragment_container)
         currentFragment?.onResume()
      }*/
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

   override fun onBackPressed() {

      if (coordinator != null) {
         coordinator!!.back()
      } else {
         finish()
      }
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when (item.itemId) {
         R.id.action_events -> {
            navigateToEvents(this)
         }
         R.id.action_shop -> {
            splitInstallRequester.requestFeature(
                  featureName = "Shop",
                  featureInstalled = navigateToShop(this)
            )
         }
         R.id.action_profile -> {
            navigateToProfile(this)
         }
         else -> {
            //not needed
         }
      }
      return true
   }

   protected fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }
}