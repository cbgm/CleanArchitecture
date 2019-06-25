package com.distribution.christian.cleantest.auth.core.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.core.core.ui.BaseStandaloneActivity
import com.distribution.christian.cleantest.core.core.ui.OnBackPressedListener


class AuthActivity: BaseStandaloneActivity(R.layout.activity_auth) {
   lateinit var  navHostFragment: NavHostFragment
   lateinit var navController: NavController

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      navHostFragment = supportFragmentManager.findFragmentById(R.id.feature_container) as NavHostFragment
   }

   override fun onBackPressed() {
      val currentFragment = navHostFragment.childFragmentManager.fragments[0]
      if (currentFragment is OnBackPressedListener)
         (currentFragment as OnBackPressedListener).onBackPressed()
   }
}