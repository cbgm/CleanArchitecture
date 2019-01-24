package com.distribution.christian.cleantest.event.data.repository.local

import com.distribution.christian.cleantest.core.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import io.reactivex.Completable
import io.reactivex.Single

interface EventDao {
   fun getEvents(): Single<EventOverview>

   fun saveEvents(data: EventOverview): Completable

   fun getEventById(eventId: String): Single<Event>
}