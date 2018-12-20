package com.example.christian.cleantest.core.core.util.extension

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
   val fragmentTransaction = beginTransaction()
   fragmentTransaction.func()
   fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
   fragmentTransaction.commit()
}

private fun FragmentManager.inBackStack(): Boolean {
   return if (backStackEntryCount > 1) {
      popBackStackImmediate()
      beginTransaction().commit()
      true
   } else {
      false
   }

}

fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String) {
   supportFragmentManager.inTransaction {
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)

   }
}

fun FragmentActivity.backStack() {
   if (!supportFragmentManager.inBackStack())
      finish()
}