package com.distribution.christian.cleantest.app

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.ui.SmartPagerAdapter

class BottomNavigationAdapter(fragmentManager: FragmentManager) : SmartPagerAdapter(
      fragmentManager
) {
   private val fragments = ArrayList<BaseFeatureFragment<BaseNavigationActivity>>()

   // Our custom method that populates this Adapter with Fragments
   fun addFragments(fragment: BaseFeatureFragment<BaseNavigationActivity>) {
      fragments.add(fragment)
   }

   override fun getItem(position: Int): BaseFeatureFragment<BaseNavigationActivity> {
      return fragments[position]
   }

   override fun getCount() = 3
}