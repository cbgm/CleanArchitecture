package com.distribution.christian.cleantest.core.core.util.ondemand

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus


@Suppress("UNUSED_EXPRESSION", "UNUSED_ANONYMOUS_PARAMETER", "UNUSED_VARIABLE")
class SplitInstallRequester(context: Context) {

   private lateinit var statusListener: SplitInstallStatusListener
   private var splitInstallManager = SplitInstallManagerFactory.create(context)
   lateinit var featureName: String

   fun requestFeature(
         featureName: String,
         statusListener: SplitInstallStatusListener
   ) {

      this.featureName = featureName
      this.statusListener = statusListener

      val request = SplitInstallRequest.newBuilder()
            .addModule(this.featureName)
            .build()
      splitInstallManager.registerListener(listener)
      splitInstallManager.startInstall(request)

   }

   fun removeFeature(featureName: String) {
      splitInstallManager.deferredUninstall(listOf(featureName))
   }

   private fun cleanUp() {
      splitInstallManager.unregisterListener(listener)
   }


   fun isFeatureAvailable(featureName: String) =
         splitInstallManager.installedModules.contains(
               (featureName)
         )

   private val listener = SplitInstallStateUpdatedListener { state ->
      val multiInstall = state.moduleNames().size > 1
      state.moduleNames()
            .forEach { name ->
               when (state.status()) {
                  SplitInstallSessionStatus.DOWNLOADING -> {
                     statusListener.updateInstallState(state, "downloading feature $featureName...")
                  }
                  SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                     statusListener.updateInstallState(state, "confirmation feature $featureName...")
                  }
                  SplitInstallSessionStatus.INSTALLED -> {
                     statusListener.updateInstallState(state, "installed feature $featureName")
                     cleanUp()
                  }

                  SplitInstallSessionStatus.INSTALLING -> {
                     statusListener.updateInstallState(state, "installing feature $featureName...")
                  }

                  SplitInstallSessionStatus.FAILED -> {
                     statusListener.updateInstallState(state, "failed feature $featureName")
                     cleanUp()
                  }
               }
            }
   }

   interface SplitInstallStatusListener {
      fun updateInstallState(state: SplitInstallSessionState, message: String)
   }
}