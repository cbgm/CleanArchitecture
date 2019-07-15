package com.distribution.christian.cleantest.core.core.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.View
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.bindScope
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
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

fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String): Fragment {
   supportFragmentManager.inTransaction {
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)
   }
   return fragment
}

fun FragmentActivity.replaceFragmentNoBackStack(
      fragment: Fragment,
      frameId: Int,
      backStackTag: String
) {
   supportFragmentManager.inTransaction {
      replace(frameId, fragment, backStackTag)

   }
}

fun <T : BaseNavigationActivity> FragmentActivity.showFragment(
      currentFeature: BaseFeatureFragment<T>?,
      newFeature: BaseFeatureFragment<T>
) {
   supportFragmentManager.beginTransaction()
         .apply {
            if (currentFeature != null) {
               hide(currentFeature)
            }
            show(newFeature)
            commitNow()
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

fun FragmentActivity.backStackClean()
      : Boolean {
   return if (supportFragmentManager.backStackEntryCount > 1) {
      supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
      true
   } else {
      false
   }
}

fun FragmentActivity.getAndCreateScope(scope: DiScope): Scope {
   return getKoin().getOrCreateScope(scope.identifier, named(scope.identifier)).apply {
      bindScope(this)
   }
}