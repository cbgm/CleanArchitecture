package com.example.christian.cleantest.core.core.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.example.christian.cleantest.core.R
import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.util.extension.navigateToCart
import com.example.christian.cleantest.core.core.util.extension.navigateToshop
import com.example.christian.cleantest.core.core.util.ondemand.SplitInstallRequester
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.android.synthetic.main.toolbar.toolbar
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
   open val coordinator: BaseCoordinator? = null
   lateinit var bottomNavigationView: BottomNavigationView
   private val splitInstallRequester: SplitInstallRequester by inject()


   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
      initBottomNavigation()
      setSupportActionBar(toolbar)
      supportFragmentManager.addOnBackStackChangedListener {
         val currentFragment = supportFragmentManager
               .findFragmentById(R.id.fragment_container)
         currentFragment?.onResume()
      }
   }

   override fun onBackPressed() {

      if (coordinator != null) {
         coordinator!!.back()
      } else {
         finish()
      }
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when (item.itemId) {
         R.id.action_cart -> {
            navigateToCart(this)
         }
         R.id.action_shop -> {
            splitInstallRequester.requestFeature("shop", listener, featureAlreadyExists())
         }
         else -> {
            //not needed
         }
      }
      return true
   }

   private fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }

   private val listener = SplitInstallStateUpdatedListener { state ->
      val multiInstall = state.moduleNames().size > 1
      state.moduleNames().forEach { name ->
         // Handle changes in state.
         when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
               //  In order to see this, the application has to be uploaded to the Play Store.
               //displayLoadingState(state, "Downloading $name")
               Toast.makeText(this, "downloading", Toast.LENGTH_SHORT).show()
            }
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
               /*
                 This may occur when attempting to download a sufficiently large module.
                 In order to see this, the application has to be uploaded to the Play Store.
                 Then features can be requested until the confirmation path is triggered.
                */
               //startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
            }
            SplitInstallSessionStatus.INSTALLED -> {
               Toast.makeText(this, "installed", Toast.LENGTH_SHORT).show()
               navigateToshop(this)
            }

            SplitInstallSessionStatus.INSTALLING ->
               Toast.makeText(this, "installing..", Toast.LENGTH_SHORT).show()

            SplitInstallSessionStatus.FAILED -> {
               //toastAndLog("Error: ${state.errorCode()} for module ${state.moduleNames()}")
               Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()

            }
         }
      }
   }

   private fun featureNotLoaded() {

   }

   private fun featureLoaded() {

   }

   private fun featureAlreadyExists() {
      navigateToshop(this)
   }
}