package com.distribution.christian.cleantest.profile.presentation.overview.model

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewFragment


data class ProfileOverviewFragmentConsistency(
      override var transitionInformation: BaseFragment.TransitionInformation? = null
) : FragmentConsistency() {

   companion object {

      fun deserializeFrom(overviewFragment: OverviewFragment): ProfileOverviewFragmentConsistency {
         return ProfileOverviewFragmentConsistency()
      }
   }
}