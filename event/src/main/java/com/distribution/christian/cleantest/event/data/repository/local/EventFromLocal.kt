package com.distribution.christian.cleantest.event.data.repository.local

import com.distribution.christian.cleantest.core.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EventFromLocal @Inject constructor() {
   private val eventDao = DaoFactory.getDaoFactory(
         DaoFactory.SQL
   )
         .getUserDao()

   fun getEvents(): Single<EventOverview> = eventDao.getEvents()

   fun saveEvents(data: EventOverview): Completable = eventDao.saveEvents(data)

   fun getEventById(eventId: String): Single<Event> = eventDao.getEventById(eventId)
}