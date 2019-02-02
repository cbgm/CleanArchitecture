package com.distribution.christian.cleantest.event.data.mapper

import com.distribution.christian.cleantest.event.data.model.EventDto
import com.distribution.christian.cleantest.event.domain.model.Event


abstract class EventDtoMapper {

   companion object {
      fun transform(event: EventDto): Event {
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

      fun transform(event: Event): EventDto {
         return EventDto(
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