package com.distribution.christian.cleantest.event.domain.usecase

import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.core.domain.model.Result

class GetEventById constructor(private val eventRepository: EventRepository) : SingleUseCase<Event, String>() {

   override suspend fun buildUseCaseObservable(param: String): Result<Event> {
      return eventRepository.getEventById(param)
   }


}