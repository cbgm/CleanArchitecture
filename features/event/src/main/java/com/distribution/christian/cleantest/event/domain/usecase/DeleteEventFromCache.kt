package com.distribution.christian.cleantest.event.domain.usecase

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.repository.EventRepository


class DeleteEventFromCache constructor(private val eventRepository: EventRepository) : SingleUseCase<Event, Event>() {

   override suspend fun buildUseCaseObservable(param: Event): Result<Event> {
      eventRepository.updateEvent(param)
      return Result.Success(param.copy(isStarred = false))
   }
}