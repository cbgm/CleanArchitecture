package com.distribution.christian.cleantest.social.core.ui

import android.os.Bundle
import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import com.distribution.christian.cleantest.social.R
import com.distribution.christian.cleantest.social.core.navigation.SocialFlowCoordinatorImpl
import org.koin.android.ext.android.inject


class SocialActivity : BaseActivity() {

   override val coordinator: SocialFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      bottomNavigationView.menu.findItem(R.id.action_social).isChecked = true
      coordinator.start(this)
   }

   override fun onBackPressed() {
      coordinator.back()
   }
}