package com.distribution.christian.cleantest.event.presentation.overview.model

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.OverviewFragment

data class EventOverviewFragmentConsistency(
      var data: ArrayList<EventEntity>? = null,
      var posToReload: Int = -1,
      override var transitionInformation: BaseFragment.TransitionInformation? = null
) : FragmentConsistency() {

   companion object {

      const val DATA_KEY = "data_key"
      const val POS_KEY = "pos_key"

      fun deserializeFrom(overviewFragment: OverviewFragment): EventOverviewFragmentConsistency {
         return EventOverviewFragmentConsistency(
               data = overviewFragment.arguments?.get(DATA_KEY)?.let { it as ArrayList<EventEntity> },
               posToReload = overviewFragment.arguments?.get(POS_KEY) as Int
         )
      }
   }
}