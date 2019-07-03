package com.distribution.christian.cleantest.core.core.util.extension

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.core.core.ui.BaseWindowFragment


fun FragmentActivity.showStarsDialog() {
   val newFragment = Class.forName("com.distribution.christian.cleantest.event.presentation.stars.StarsDialog").newInstance() as BaseWindowFragment
   newFragment.show(this.supportFragmentManager, "Stars")
}