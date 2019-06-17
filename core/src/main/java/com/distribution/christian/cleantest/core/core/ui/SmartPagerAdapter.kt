package com.distribution.christian.cleantest.core.core.ui

import android.view.ViewGroup
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


abstract class SmartPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
      fragmentManager
) {
   // Sparse array to keep track of registered fragments in memory
   private val registeredFragments = SparseArray<BaseFeatureFragment<BaseNavigationActivity>>()

   // Register the currentFragment when the item is instantiated
   override fun instantiateItem(container: ViewGroup, position: Int): Any {
      val fragment = super.instantiateItem(container, position) as BaseFeatureFragment<BaseNavigationActivity>
      registeredFragments.put(position, fragment)
      return fragment
   }

   // Unregister when the item is inactive
   override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
      registeredFragments.remove(position)
      super.destroyItem(container, position, `object`)
   }

   // Returns the currentFragment for the position (if instantiated)
   fun getRegisteredFragment(position: Int): Fragment {
      return registeredFragments.get(position)
   }
}