package com.distribution.christian.cleantest.event.presentation.detail.model

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency
import com.distribution.christian.cleantest.event.presentation.detail.DetailFragment

data class EventDetailFragmentConsistency(
      var eventId: String? = null,
      var event: EventEntity? = null,
      override var transitionInformation: BaseFragment.TransitionInformation? = null
) : FragmentConsistency() {

   companion object {

      const val Event_ID_KEY = "event_id_key"
      const val EVENT_KEY = "event_key"

      fun deserializeFrom(overviewFragment: DetailFragment): EventDetailFragmentConsistency {
         return EventDetailFragmentConsistency(
               eventId = overviewFragment.arguments?.get(Event_ID_KEY)?.let { it as String },
               transitionInformation = overviewFragment.arguments?.get(TRANSITION_KEY)?.let { it as BaseFragment.TransitionInformation },
               event = overviewFragment.arguments?.get(EVENT_KEY)?.let { it as EventEntity }
         )
      }
   }
}