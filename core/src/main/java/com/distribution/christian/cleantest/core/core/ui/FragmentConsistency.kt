package com.distribution.christian.cleantest.core.core.ui

abstract class FragmentConsistency {

   companion object {
      const val TRANSITION_KEY = "transition_key"
   }

   abstract var transitionInformation: BaseFragment.TransitionInformation?
}