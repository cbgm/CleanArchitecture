package com.distribution.christian.cleantest.social.presentation.overview.model

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency
import com.distribution.christian.cleantest.social.presentation.overview.OverviewFragment

data class SocialOverviewFragmentConsistency(
      override var transitionInformation: BaseFragment.TransitionInformation? = null
) : FragmentConsistency() {

   companion object {

      fun deserializeFrom(overviewFragment: OverviewFragment): SocialOverviewFragmentConsistency {
         return SocialOverviewFragmentConsistency()
      }
   }
}