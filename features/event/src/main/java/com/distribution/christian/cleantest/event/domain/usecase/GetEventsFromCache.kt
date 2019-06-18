package com.distribution.christian.cleantest.event.domain.usecase

import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.event.data.cache.EventCache
import com.distribution.christian.cleantest.event.data.mapper.EventDtoMapper
import com.distribution.christian.cleantest.event.domain.model.Event


class GetEventsFromCache constructor(private val eventCache: EventCache) : SingleUseCase<EventOverview, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<EventOverview> {
      val eventsFromCache = eventCache.getEventDao()
            .getEventsByCriteria()
      return Result.Success(
            EventOverview(
                  eventsFromCache.size,
                  eventsFromCache.map { EventDtoMapper.transform(it) } as ArrayList<Event>))
   }
}