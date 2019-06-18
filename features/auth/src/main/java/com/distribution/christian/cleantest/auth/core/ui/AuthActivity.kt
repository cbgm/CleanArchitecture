package com.distribution.christian.cleantest.auth.core.ui

import android.os.Bundle
import com.distribution.christian.cleantest.auth.R
import com.distribution.christian.cleantest.core.core.ui.BaseStandaloneActivity


class AuthActivity: BaseStandaloneActivity(R.layout.activity_auth) {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      supportFragmentManager.beginTransaction()
            .add(R.id.feature_container, AuthFeatureFragment.newInstance(), AuthFeatureFragment.TAG)
            .commit()
   }
}