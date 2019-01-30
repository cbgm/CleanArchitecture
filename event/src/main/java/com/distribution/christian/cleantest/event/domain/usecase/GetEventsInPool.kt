package com.distribution.christian.cleantest.event.domain.usecase

import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.core.domain.model.Result

class GetEventsInPool constructor(private val eventRepository: EventRepository) : SingleUseCase<EventOverview, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<EventOverview> {
      return eventRepository.getAllEvents()
   }

}