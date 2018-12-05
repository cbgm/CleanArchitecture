package com.example.christian.cleantest.core.core.logging

import android.util.Log
import timber.log.Timber

class TimberTree: Timber.DebugTree() {

   override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {

      //just for testing
      Log.e("test", "from debug")
   }
}