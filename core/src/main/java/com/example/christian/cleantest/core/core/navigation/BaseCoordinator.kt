package com.example.christian.cleantest.core.core.navigation

import android.support.v4.app.FragmentActivity

abstract class BaseCoordinator {

   lateinit var activity: FragmentActivity

   open fun start(fragmentActivity: FragmentActivity) {
      activity = fragmentActivity
   }

   abstract fun back()
}