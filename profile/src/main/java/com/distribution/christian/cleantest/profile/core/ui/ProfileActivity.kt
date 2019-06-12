package com.distribution.christian.cleantest.profile.core.ui

import android.os.Bundle
import com.distribution.christian.cleantest.core.core.ui.NavigationActivity
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.core.navigation.ProfileFlowCoordinatorImpl
import org.koin.android.ext.android.inject


class ProfileActivity : NavigationActivity() {

   override val coordinator: ProfileFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      bottomNavigationView.menu.findItem(R.id.action_profile).isChecked = true
      coordinator.start(this)
   }

   override fun onBackPressed() {
      coordinator.back()
   }
}