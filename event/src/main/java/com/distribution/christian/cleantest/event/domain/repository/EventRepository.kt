package com.distribution.christian.cleantest.event.domain.repository

import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.event.domain.model.Event

interface EventRepository {

    suspend fun getAllEvents(): Result<EventOverview>

    suspend fun updateEvent(event: Event): Result<Event>

    suspend fun getEventById(eventId: String): Result<Event>
}