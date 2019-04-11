package com.distribution.christian.cleantest.event.presentation.overview.model

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency
import com.distribution.christian.cleantest.core.core.util.listener.ObserableVariable
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.OverviewFragment

@Suppress("UNCHECKED_CAST")
data class EventOverviewFragmentConsistency(
      var data: ArrayList<EventEntity>? = null,
      var posToReload: Int = -1,
      override var transitionInformation: BaseFragment.TransitionInformation? = null,
      var searchTerm: ObserableVariable<String>
) : FragmentConsistency() {

   companion object {

      const val DATA_KEY = "data_key"
      const val POS_KEY = "pos_key"
      const val SEARCH_TERM_KEY = "search_term_key"

      fun deserializeFrom(overviewFragment: OverviewFragment): EventOverviewFragmentConsistency {
         return EventOverviewFragmentConsistency(
               data = overviewFragment.arguments?.get(DATA_KEY)?.let { it as ArrayList<EventEntity> },
               posToReload = overviewFragment.arguments?.get(POS_KEY) as Int,
               searchTerm = ObserableVariable(overviewFragment.arguments?.get(SEARCH_TERM_KEY)?.let { it as String }?: ""))
      }
   }
}