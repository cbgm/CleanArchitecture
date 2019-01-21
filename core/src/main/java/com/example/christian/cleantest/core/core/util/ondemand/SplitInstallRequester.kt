package com.example.christian.cleantest.core.core.util.ondemand

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class SplitInstallRequester(context: Context) {

   private var splitInstallManager = SplitInstallManagerFactory.create(context)

   private var featureLoading: Unit? = null
   private var featureInstalled: Unit? = null
   private var featureInstalling: Unit? = null
   private var featureRequiresConfirmation: Unit? = null
   private var featureLoadingFailed: Unit? = null

   fun requestFeature(
         featureName: String,
         featureLoading: Unit? = null,
         featureInstalled: Unit? = null,
         featureInstalling: Unit? = null,
         featureRequiresConfirmation: Unit? = null,
         featureLoadingFailed: Unit? = null
   ) {

      this.featureInstalled = featureInstalled
      this.featureInstalling = featureInstalling
      this.featureLoading = featureLoading
      this.featureRequiresConfirmation = featureRequiresConfirmation
      this.featureLoadingFailed = featureLoadingFailed

      val request = SplitInstallRequest.newBuilder()
            .addModule(featureName)
            .build()

      splitInstallManager.registerListener(listener)
      splitInstallManager.startInstall(request)

   }

   private fun cleanUp() {
      featureLoading = null
      featureInstalled = null
      featureInstalling = null
      featureRequiresConfirmation = null
      featureLoadingFailed = null
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
               // Handle changes in state.
               when (state.status()) {
                  SplitInstallSessionStatus.DOWNLOADING -> {
                     featureLoading
                  }
                  SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                     featureRequiresConfirmation
                  }
                  SplitInstallSessionStatus.INSTALLED -> {
                     cleanUp()
                     featureInstalled
                  }

                  SplitInstallSessionStatus.INSTALLING -> {
                     featureLoading
                  }

                  SplitInstallSessionStatus.FAILED -> {
                     cleanUp()
                     featureLoadingFailed
                  }
               }
            }
   }
}