package com.distribution.christian.cleantest.event.presentation.stars.model

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency

data class EventStarsFragmentConsistency(
      override var transitionInformation: BaseFragment.TransitionInformation? = null
) : FragmentConsistency() {

}