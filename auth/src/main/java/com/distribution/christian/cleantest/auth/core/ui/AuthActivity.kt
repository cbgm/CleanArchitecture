package com.distribution.christian.cleantest.auth.core.ui

import android.os.Bundle
import android.view.MenuItem
import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.auth.core.navigation.AuthFlowCoordinatorImpl
import org.koin.android.ext.android.inject


class AuthActivity: BaseActivity(R.layout.activity_auth) {

   override val coordinator: AuthFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      coordinator.start(this, false)
      coordinator.showLogin()
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {
      /*return when (item?.itemId) {
         android.R.id.home -> {
            coordinator.back()
            true
         }
         else -> false
      }*/
      return true
   }
}