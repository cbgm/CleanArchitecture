package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import android.view.MenuItem
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
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

   abstract fun setBottomNavigationItem(state: CoordinatorManager.State)

   open fun initBottomNavigation() {}

   override fun onBackPressed() {
      coordinatorManager.backStackFeature()
   }
}