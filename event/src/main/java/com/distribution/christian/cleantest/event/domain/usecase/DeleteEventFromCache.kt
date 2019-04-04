package com.distribution.christian.cleantest.event.domain.usecase

import com.distribution.christian.cleantest.core.domain.completable.CompletableUseCase
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.event.data.cache.EventCache
import com.distribution.christian.cleantest.event.data.mapper.EventDtoMapper
import com.distribution.christian.cleantest.event.domain.model.Event


class DeleteEventFromCache constructor(private val eventCache: EventCache) : CompletableUseCase<Event>() {

   override suspend fun buildUseCaseObservable(param: Event): Result<Any> {
      eventCache.getEventDao().deleteEvent(EventDtoMapper.transform(param))
      return Result.Complete()
   }
}