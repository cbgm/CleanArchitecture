package com.distribution.christian.cleantest.core.core.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.View
import com.distribution.christian.cleantest.core.core.di.DiScope
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.core.scope.Scope


private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
   val fragmentTransaction = beginTransaction()
   fragmentTransaction.func()
   //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
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

fun FragmentActivity.replaceFragmentwithSharedElement(
      fragment: Fragment,
      frameId: Int,
      backStackTag: String,
      sharedElement: View,
      transitionName: String
) {
   supportFragmentManager.inTransaction {
      addSharedElement(sharedElement, transitionName)
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)

   }
}


fun FragmentActivity.backStack() {
   if (!supportFragmentManager.inBackStack())
      finish()
}

fun FragmentActivity.updateScope(scope: DiScope): Scope? {
   val session = getOrCreateScope(scope.identifier)
   bindScope(session)
   return  session
}