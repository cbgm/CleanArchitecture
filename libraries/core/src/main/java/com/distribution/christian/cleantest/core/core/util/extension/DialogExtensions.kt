package com.distribution.christian.cleantest.core.core.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.core.core.ui.BaseWindowFragment


fun FragmentActivity.showStarsDialog(currentFeatureFragment: Fragment) {
   val newFragment = Class.forName("com.distribution.christian.cleantest.event.presentation.stars.StarsDialog").newInstance() as BaseWindowFragment
   newFragment.setTargetFragment(currentFeatureFragment, 0)
   newFragment.show(this.supportFragmentManager, "Stars")
}