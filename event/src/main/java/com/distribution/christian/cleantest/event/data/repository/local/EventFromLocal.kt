package com.distribution.christian.cleantest.event.data.repository.local

import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.domain.model.Result

import javax.inject.Inject


class EventFromLocal @Inject constructor() {

   private val eventDao = DaoFactory.getDaoFactory(
         DaoFactory.SQL
   )
         .getEventDao()

   fun getEvents(): Result<EventOverview> = eventDao.getEvents()

   fun saveEvents(data: EventOverview): Result<Nothing> = eventDao.saveEvents(data)

   fun getEventById(eventId: String): Result<Event> = eventDao.getEventById(eventId)

   fun updateEvent(event: Event): Result<Event> = eventDao.updateEvent(event)
}