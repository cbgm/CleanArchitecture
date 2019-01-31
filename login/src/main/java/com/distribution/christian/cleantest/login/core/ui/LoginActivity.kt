package com.distribution.christian.cleantest.login.core.ui

import android.os.Bundle
import android.view.MenuItem
import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import com.distribution.christian.cleantest.login.core.navigation.LoginFlowCoordinatorImpl
import org.koin.android.ext.android.inject

class LoginActivity: BaseActivity() {

   //override val coordinator: LoginFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      //coordinator.start(this)
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