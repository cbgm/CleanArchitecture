package com.distribution.christian.cleantest.core.core.util.extension

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


inline fun <FRAGMENT : Fragment> FRAGMENT.args(argsBuilder: Bundle.() -> Unit): FRAGMENT = this.apply {
   arguments = Bundle().apply(
         argsBuilder
   )
}

inline fun Fragment.argsUpdate(args: Bundle.() -> Unit) {
   this.arguments?.args()
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String) {
   childFragmentManager.inTransaction {
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)
   }
}

fun Fragment.replaceFragmentwithSharedElement(
      fragment: Fragment,
      frameId: Int,
      backStackTag: String,
      sharedElement: View,
      transitionName: String
) {
   childFragmentManager.inTransaction {
      addSharedElement(sharedElement, transitionName)
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)

   }
}

fun Fragment.backStack() {
   if (!childFragmentManager.inBackStack())
      activity?.finish()
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