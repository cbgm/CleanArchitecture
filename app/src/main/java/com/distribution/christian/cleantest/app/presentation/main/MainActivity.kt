package com.distribution.christian.cleantest.app.presentation.main

import android.os.Bundle
import android.view.MenuItem
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.navigation.FrankenCoordinatorManager.States.*
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity


class MainActivity : BaseNavigationActivity(R.layout.activity_main) {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      coordinatorManager.startNavigation(this)
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {

      when (item.itemId) {
         R.id.action_events -> {
            coordinatorManager.navigateToFeature(EVENTS)
         }
         R.id.action_shop -> {
            splitInstallRequester.requestFeature(
                  featureName = "Shop",
                  featureInstalled = coordinatorManager.navigateToFeature(SHOP)
            )
         }
         R.id.action_profile -> {
            coordinatorManager.navigateToFeature(PROFILE)
         }
      }
      return true
   }

   override fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }

   override fun setBottomNavigationItem(state: CoordinatorManager.State) {
      val item = when(state) {
         EVENTS -> R.id.action_events
         SHOP -> R.id.action_shop
         PROFILE -> R.id.action_profile
         else -> R.id.action_events
      }
      bottomNavigationView.menu.findItem(item).run {

         if(!isChecked){
            isChecked = true
         }
      }
   }
}