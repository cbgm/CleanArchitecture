package com.distribution.christian.cleantest.core.core.ui

import android.content.Context
import com.google.android.gms.common.wrappers.InstantApps
import com.google.android.play.core.splitcompat.SplitCompat

abstract class BaseSplitActivity: BaseActivity() {

   override fun attachBaseContext(newBase: Context?) {
      super.attachBaseContext(newBase)
      if (!InstantApps.isInstantApp(this)) {
         SplitCompat.install(this);
      }

   }
}