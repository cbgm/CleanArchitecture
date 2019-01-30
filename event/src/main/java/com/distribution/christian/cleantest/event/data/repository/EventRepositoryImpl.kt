package com.distribution.christian.cleantest.event.data.repository

import com.distribution.christian.cleantest.core.device.NetManager
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.event.data.repository.remote.event.EventFromNetwork
import com.distribution.christian.cleantest.event.data.repository.local.EventFromLocal
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.event.domain.model.Event

class EventRepositoryImpl constructor(
      private val netManager: NetManager,
      private val eventFromNetwork: EventFromNetwork,
      private val eventFromLocal: EventFromLocal
) : EventRepository {

   override suspend fun getAllEvents(): Result<EventOverview> {
      netManager.isConnected.let {

      }
      return eventFromLocal.getEvents()
   }

   override suspend fun updateEvent(event: Event): Result<Event> {
      return eventFromLocal.updateEvent(event)
   }

   override suspend fun getEventById(eventId: String): Result<Event> {
      return eventFromLocal.getEventById(eventId)
   }
}