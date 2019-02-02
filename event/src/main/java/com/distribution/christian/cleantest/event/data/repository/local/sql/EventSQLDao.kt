package com.distribution.christian.cleantest.event.data.repository.local.sql

import com.distribution.christian.cleantest.event.core.mock.EventGenerator
import com.distribution.christian.cleantest.event.data.model.EventDto
import com.distribution.christian.cleantest.event.data.repository.local.EventDao
import com.distribution.christian.cleantest.event.data.mapper.EventDtoMapper
import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.domain.model.Result


class EventSQLDao : EventDao {


   companion object {
      var events = ArrayList<EventDto>()
   }

   init {
      events.addAll(EventGenerator.generate(10))
   }


   override fun getEvents(): Result<EventOverview> {
      return Result.Success(
            EventOverview(
                  10,
                  (events.map { event ->
                     EventDtoMapper.transform(
                           event
                     )
                  } as ArrayList<Event>)
            )
      )
   }

   override fun saveEvents(data: EventOverview): Result<Nothing> {
      return Result.Complete()
   }

   override fun getEventById(eventId: String): Result<Event> {
      return Result.Success(
            EventDtoMapper.transform(events.first { it.id.toString() == eventId })
      )
   }

   override fun updateEvent(event: Event): Result<Event> {
      events.forEach {
         if (it.id == event.id) {
            it.isStarred = !it.isStarred
            return Result.Success(EventDtoMapper.transform(it))
         }
      }
      return Result.Error(Exception())
   }
}