package com.distribution.christian.cleantest.app.presentation.main

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.ui.OnBackPressedListener


class MainActivity : BaseNavigationActivity(R.layout.activity_main) {
   lateinit var  navHostFragment: NavHostFragment
   lateinit var navController: NavController

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      navHostFragment = supportFragmentManager.findFragmentById(R.id.feature_container) as NavHostFragment
      navController = navHostFragment.navController
      NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)
   }

   override fun onResume() {
      super.onResume()
      val appLinkIntent = intent
      val appLinkData = appLinkIntent.data
      appLinkData?.let { navController.navigate(it) }

   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when (item.itemId) {
         R.id.action_events -> {
            navController.navigate(R.id.eventFeatureFragment)
         }
         R.id.action_shop -> {
            splitInstallRequester.requestFeature(
                  featureName = "Shop",
                  featureInstalled = navController.navigate(R.id.shopFeatureFragment)
            )
         }
         R.id.action_profile -> {
            navController.navigate(R.id.profileFeatureFragment)
         }
      }
      return true
   }

   override fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }

   override fun onBackPressed() {
      val currentFragment = navHostFragment.childFragmentManager.fragments[0]
      if (currentFragment is OnBackPressedListener)
         (currentFragment as OnBackPressedListener).onBackPressed()
   }
}