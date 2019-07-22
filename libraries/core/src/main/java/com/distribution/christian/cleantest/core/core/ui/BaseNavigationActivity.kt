package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.toolbar.appBarLayout
import org.koin.android.ext.android.inject

abstract class BaseNavigationActivity(mainLayout: Int) : BaseActivity<BaseNavigationActivity>(mainLayout), BottomNavigationView.OnNavigationItemSelectedListener {
   lateinit var bottomNavigationView: BottomNavigationView
   val splitInstallRequester: SplitInstallRequester by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      initBottomNavigation()
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {return true}

   open fun setBottomNavigationItem(state: CoordinatorManager.State) {
      showNavigationElements()
   }

   open fun initBottomNavigation() {}

   fun hideNavigationElements() {
      appBarLayout.visibility = View.GONE
      bottomNavigationView.visibility = View.GONE
   }

   private fun showNavigationElements() {
      appBarLayout.visibility = View.VISIBLE
      bottomNavigationView.visibility = View.VISIBLE
   }

   override fun onBackPressed() {
      coordinatorManager.backStackFeature()
   }
}