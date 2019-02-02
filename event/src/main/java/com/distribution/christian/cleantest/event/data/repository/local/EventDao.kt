package com.distribution.christian.cleantest.event.data.repository.local

import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.domain.model.Result


interface EventDao {

   fun getEvents(): Result<EventOverview>

   fun saveEvents(data: EventOverview): Result<Nothing>

   fun updateEvent(event: Event): Result<Event>

   fun getEventById(eventId: String): Result<Event>
}