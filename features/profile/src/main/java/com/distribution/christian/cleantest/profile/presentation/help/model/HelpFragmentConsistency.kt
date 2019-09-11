package com.distribution.christian.cleantest.profile.presentation.help.model

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency
import com.distribution.christian.cleantest.profile.presentation.overview.OverviewFragment


data class HelpFragmentConsistency(
      override var transitionInformation: BaseFragment.TransitionInformation? = null
) : FragmentConsistency() {

   companion object {

      fun deserializeFrom(overviewFragment: OverviewFragment): HelpFragmentConsistency {
         return HelpFragmentConsistency()
      }
   }
}