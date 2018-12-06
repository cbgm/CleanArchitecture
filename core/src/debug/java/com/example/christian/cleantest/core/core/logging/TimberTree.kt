package com.example.christian.cleantest.core.core.logging

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

class TimberTree: Timber.DebugTree() {

   @SuppressLint("LogNotTimber")
   override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {

      //just for testing
      Log.e("test", "from debug")
   }
}