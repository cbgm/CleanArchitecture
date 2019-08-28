package com.distribution.christian.cleantest.app.presentation.main

import android.os.Bundle
import android.view.MenuItem
import com.christian.multinavlib.navigation.coordinator.BaseCoordinator
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.FeatureStates
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.ui.InstallFeatureDialog
import com.distribution.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus


class MainActivity : BaseNavigationActivity(R.layout.activity_main), SplitInstallRequester.SplitInstallStatusListener {

   private var featureDialog: InstallFeatureDialog? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      coordinatorManager.startNavigation(this, null, false)
      coordinatorManager.navigateToFeature(FeatureStates.AUTH)
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {

      when (item.itemId) {
         R.id.action_events -> {
            coordinatorManager.navigateToFeature(FeatureStates.EVENTS)
         }
         R.id.action_shop -> {
            if (splitInstallRequester.isFeatureAvailable("shop")) {
               showShop()
            } else {
               showDownloadDialog()
               splitInstallRequester.requestFeature("shop", this)
            }
         }
         R.id.action_profile -> {
            coordinatorManager.navigateToFeature(FeatureStates.PROFILE)
         }
      }
      return true
   }

   override fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }

   override fun updateInstallState(state: SplitInstallSessionState, message: String) {

      when (state.status()) {
         SplitInstallSessionStatus.INSTALLED -> {
            featureDialog?.dismiss()
            featureDialog = null
            showShop()
         }
         SplitInstallSessionStatus.DOWNLOADING -> {
            featureDialog?.setProgress(state.totalBytesToDownload(), state.bytesDownloaded())
         }
      }
      featureDialog?.setText(message)
   }

   private fun showDownloadDialog() {
      if (featureDialog == null) {
         val fm = supportFragmentManager
         featureDialog = InstallFeatureDialog.newInstance(
               R.layout.feature_dialog,
               "Install",
               "",
               "Feature is currently not installed. Triggering the download. Please wait until finished."
         )
         featureDialog?.show(fm, "test")
      }
   }

   private fun showShop() {
      coordinatorManager.registerFeatureCoordinator(
            FeatureStates.SHOP,
            Class.forName("com.distribution.christian.cleantest.shop.core.navigation.ShopFlowCoordinatorImpl").newInstance() as BaseCoordinator//shopCoordinator
      )
      coordinatorManager.navigateToFeature(FeatureStates.SHOP)
   }
}