package com.distribution.christian.cleantest.core.core.ui

import android.content.Context
import com.distribution.christian.cleantest.core.R
import com.google.android.gms.common.wrappers.InstantApps
import com.google.android.play.core.splitcompat.SplitCompat


abstract class BaseSplitActivity: BaseNavigationActivity(R.layout.activity_main) {

   override fun attachBaseContext(newBase: Context?) {
      super.attachBaseContext(newBase)
      if (!InstantApps.isInstantApp(this)) {
         SplitCompat.install(this)
      }

   }
}