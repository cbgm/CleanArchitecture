package com.distribution.christian.cleantest.event.domain.repository

import com.distribution.christian.cleantest.core.data.model.EventDto
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.Event

interface EventRepository {

    suspend fun getAllEvents(): Result<EventOverview>

    suspend fun saveEvent(event: EventDto): Result<Nothing>

    suspend fun getEventById(eventId: String): Result<Event>
}