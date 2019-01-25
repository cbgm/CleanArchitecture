package com.distribution.christian.cleantest.event.domain.usecases

import com.distribution.christian.cleantest.core.domain.model.Event
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.event.domain.repository.EventRepository

class UpdateEvent constructor(private val eventRepository: EventRepository) : SingleUseCase<Event, Event>() {

   override suspend fun buildUseCaseObservable(param: Event): Result<Event> {
      return eventRepository.updateEvent(param)
   }
}
