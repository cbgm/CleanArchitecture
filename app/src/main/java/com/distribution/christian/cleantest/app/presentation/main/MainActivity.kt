package com.distribution.christian.cleantest.app.presentation.main

import android.os.Bundle
import android.view.MenuItem
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.app.core.navigation.MainCoordinatorImpl
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import org.koin.android.ext.android.inject


class MainActivity : BaseNavigationActivity(R.layout.activity_main) {
   private val mainCoordinator: MainCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      mainCoordinator.start(this)
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when (item.itemId) {
         R.id.action_events -> {
            mainCoordinator.showEvents()
         }
         R.id.action_shop -> {
            splitInstallRequester.requestFeature(
                  featureName = "Shop",
                  featureInstalled = mainCoordinator.showShop()
            )
         }
         R.id.action_profile -> {
            mainCoordinator.showProfile()
         }
      }
      return true
   }

   override fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }
}