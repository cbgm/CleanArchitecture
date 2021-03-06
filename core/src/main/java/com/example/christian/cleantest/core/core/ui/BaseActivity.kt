package com.example.christian.cleantest.core.core.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.christian.cleantest.core.R
import com.example.christian.cleantest.core.core.navigation.BaseCoordinator
import com.example.christian.cleantest.core.core.util.extension.navigateToCart
import com.example.christian.cleantest.core.core.util.extension.navigateToshop
import kotlinx.android.synthetic.main.toolbar.toolbar

abstract class BaseActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
   open val coordinator: BaseCoordinator? = null
   lateinit var bottomNavigationView: BottomNavigationView

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
            navigateToshop(this)
         }
         else -> {
            //not needed
         }
      }
      return true;
   }

   private fun initBottomNavigation() {
      bottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener(this)
   }
}