package com.distribution.christian.cleantest.app.presentation.main

import android.os.Bundle
import android.view.MenuItem
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.FeatureStates
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity


class MainActivity : BaseNavigationActivity(R.layout.activity_main) {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      coordinatorManager.startNavigation(this, null, false)
      coordinatorManager.navigateToFeature(FeatureStates.AUTH)
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {

      when (item.itemId) {
         R.id.action_events -> {
            coordinatorManager.navigateToFeature(FeatureStates.EVENTS)
         }
         R.id.action_shop -> {
            splitInstallRequester.requestFeature(
                  featureName = "Shop",
                  featureInstalled = coordinatorManager.navigateToFeature(FeatureStates.SHOP)
            )
         }
         R.id.action_profile -> {
            coordinatorManager.navigateToFeature(FeatureStates.PROFILE)
         }
      }
      return true
   }

   override fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }

   override fun setBottomNavigationItem(state: CoordinatorManager.State) {
      super.setBottomNavigationItem(state)
      val item = when(state) {
         FeatureStates.EVENTS -> R.id.action_events
         FeatureStates.SHOP -> R.id.action_shop
         FeatureStates.PROFILE -> R.id.action_profile
         else -> R.id.action_events
      }
      bottomNavigationView.menu.findItem(item).run {

         if(!isChecked){
            isChecked = true
         }
      }
   }
}