package com.example.christian.cleantest.core.core.util.ondemand

import android.content.Context
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener

class SplitInstallRequester(private val context: Context) {

   private var splitInstallManager = SplitInstallManagerFactory.create(context)

   fun requestFeature(
         featureName: String,
         listener: SplitInstallStateUpdatedListener,
         existsCallback: Unit
   ) {

      val request = SplitInstallRequest.newBuilder()
            .addModule(featureName)
            .build()

      if (shouldLoadFeature(featureName)) {
         splitInstallManager.registerListener(listener)
         splitInstallManager.startInstall(request)
      } else {
         existsCallback
      }
   }

   private fun shouldLoadFeature(featureName: String) =
         !splitInstallManager.installedModules.contains(
               (featureName)
         )
}