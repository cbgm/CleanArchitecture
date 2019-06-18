package com.distribution.christian.cleantest.event.data.repository

import androidx.annotation.WorkerThread
import com.distribution.christian.cleantest.core.device.NetManager
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.event.data.repository.remote.event.EventFromNetwork
import com.distribution.christian.cleantest.event.data.repository.local.EventFromLocal
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.Search
import com.distribution.christian.cleantest.event.data.cache.EventCache
import com.distribution.christian.cleantest.event.data.mapper.EventDtoMapper
import com.distribution.christian.cleantest.event.domain.model.Event


class EventRepositoryImpl constructor(
      private val netManager: NetManager,
      private val eventFromNetwork: EventFromNetwork,
      private val eventFromLocal: EventFromLocal,
      private val eventCache: EventCache
) : EventRepository {

   override suspend fun getEventsByCriteria(search: Search?): Result<EventOverview> {
      netManager.isConnected.let {

      }
      return eventFromLocal.getEventsByCriteria(search)
   }

   @WorkerThread
   override suspend fun updateEvent(event: Event): Result<Event> {
      if (!event.isStarred) {
         eventCache.getEventDao()
               .saveEvent(EventDtoMapper.transform(event))
      } else {
         eventCache.getEventDao().deleteEvent(EventDtoMapper.transform(event))
      }
      return eventFromLocal.updateEvent(event)
   }

   @WorkerThread
   override suspend fun deleteEvent(event: Event): Result<Nothing> {
      eventCache.getEventDao()
            .deleteEvent(EventDtoMapper.transform(event))
      return Result.Complete()
   }

   override suspend fun getEventById(eventId: String): Result<Event> {
      return eventFromLocal.getEventById(eventId)
   }

   @WorkerThread
   override suspend fun getEvents(): Result<EventOverview> {
      val eventsFromCache = (eventCache.getEventDao().getEventsByCriteria().map {
         EventDtoMapper.transform(
               it
         )
      }) as ArrayList<Event>
      return Result.Success(EventOverview(eventsFromCache.size, eventsFromCache))
   }
}