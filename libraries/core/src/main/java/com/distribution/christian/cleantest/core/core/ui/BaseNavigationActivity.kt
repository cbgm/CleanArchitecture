package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import android.view.MenuItem
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

abstract class BaseNavigationActivity(mainLayout: Int) : BaseActivity<BaseNavigationActivity>(mainLayout), BottomNavigationView.OnNavigationItemSelectedListener {
   lateinit var bottomNavigationView: BottomNavigationView
   val splitInstallRequester: SplitInstallRequester by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      initBottomNavigation()
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {return true}

   open fun initBottomNavigation() {}

   override fun onBackPressed() {

      if (activeFeatureFragment.coordinator != null) {
         activeFeatureFragment.coordinator!!.back()
      } else {
         finish()
      }
   }
}