package com.distribution.christian.cleantest.core.core.ui

import android.support.v4.app.Fragment
import android.view.View

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<T> : Fragment() {

   val activity: T by lazy { getActivity() as T }

   protected lateinit var transitionName: String


   data class TransitionInformation(val sharedElement: View, val transitionName: String)
}