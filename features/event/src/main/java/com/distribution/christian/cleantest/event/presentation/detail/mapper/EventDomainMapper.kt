package com.distribution.christian.cleantest.event.presentation.detail.mapper

import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity


abstract class EventDomainMapper {

   companion object {
      fun transform(event: Event): EventEntity {
         return EventEntity(
               event.id,
               event.name,
               event.city,
               event.location,
               event.date,
               event.time,
               event.price,
               event.description,
               event.isStarred
         )
      }

      fun transform(event: EventEntity): Event {
         return Event(
               event.id,
               event.name,
               event.city,
               event.location,
               event.date,
               event.time,
               event.price,
               event.description,
               event.isStarred
         )
      }
   }
}