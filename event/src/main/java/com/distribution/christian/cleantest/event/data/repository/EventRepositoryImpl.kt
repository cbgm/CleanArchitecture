package com.distribution.christian.cleantest.event.data.repository

import com.distribution.christian.cleantest.core.device.NetManager
import com.distribution.christian.cleantest.core.data.model.EventDto
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.event.data.repository.remote.event.EventFromNetwork
import com.distribution.christian.cleantest.event.data.repository.local.EventFromLocal
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.onSuccess
import com.distribution.christian.cleantest.core.domain.model.Event

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
      val result = eventFromNetwork.getUsers()
      result.onSuccess { eventFromLocal.saveEvents(it) }
      return result
   }


   override suspend fun saveEvent(event: EventDto): Result<Nothing> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }

   override suspend fun getEventById(eventId: String): Result<Event> {
      return eventFromNetwork.getEvent(eventId)
   }
}