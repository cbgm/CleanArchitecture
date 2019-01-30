package com.distribution.christian.cleantest.event.data.repository.remote.event

import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.core.util.extension.mapToResult
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.event.data.mapper.EventDtoMapper
import com.distribution.christian.cleantest.event.domain.model.Event

class EventFromNetwork constructor(private val eventApi: EventApi) {

   suspend fun getEvents(): Result<EventOverview> {
      val response = eventApi.getAllUsers()
            .await()

      return response.mapToResult {
         EventOverview(
               10,
               (response.body()!!.map { user ->
                  EventDtoMapper.transform(
                        user
                  )
               } as ArrayList<Event>))
      }
   }

   suspend fun getEvent(eventId: String): Result<Event> {
      val response = eventApi.getEventById(eventId)
            .await()

      return response.mapToResult { EventDtoMapper.transform(response.body()!!) }
   }

   suspend fun updateEvent(event: Event): Result<Event> {
      val response = eventApi.updateEvent(event.id.toString(), EventDtoMapper.transform(event))
            .await()
      return response.mapToResult { EventDtoMapper.transform(response.body()!!) }
   }
}