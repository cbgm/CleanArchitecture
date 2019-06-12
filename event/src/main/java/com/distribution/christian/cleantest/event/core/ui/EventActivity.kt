package com.distribution.christian.cleantest.event.core.ui

import android.os.Bundle
import com.distribution.christian.cleantest.core.core.ui.NavigationActivity
import com.distribution.christian.cleantest.event.core.navigation.EventFlowCoordinatorImpl
import org.koin.android.ext.android.inject


class EventActivity: NavigationActivity() {

   override val coordinator: EventFlowCoordinatorImpl by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      coordinator.start(this)
   }
}