package com.distribution.christian.cleantest.core.core.ui

import android.support.v4.app.Fragment
import android.view.View

abstract class BaseFragment: Fragment() {

   protected lateinit var transitionName: String


   data class TransitionInformation(val sharedElement: View, val transitionName: String)
}