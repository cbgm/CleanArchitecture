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
      //for different purposes
      /*netManager.isConnected.let {
          if(it)
              return eventFromNetwork.getUsers().flatMap {
                  return@flatMap eventFromLocal.saveUsers(it)
                          .toSingleDefault(it)
              }
          else
              return eventFromLocal.getUsers()
      }*/
      //if no network, local cached data is loaded(interceptor)
      return eventFromLocal.getEvents()


      /*val result = eventFromNetwork.getEvents()
      result.onSuccess { eventFromLocal.saveEvents(it) }
      return result*/
   }


   override suspend fun updateEvent(event: Event): Result<Event> {
      return eventFromLocal.updateEvent(event)
      //return eventFromNetwork.updateEvent(event)
   }

   override suspend fun getEventById(eventId: String): Result<Event> {
      return eventFromLocal.getEventById(eventId)
      //return eventFromNetwork.getEvent(eventId)
   }
}