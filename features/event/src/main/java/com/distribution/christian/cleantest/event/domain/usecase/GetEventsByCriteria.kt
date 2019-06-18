package com.distribution.christian.cleantest.event.domain.usecase

import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.repository.EventRepository
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.Search


class GetEventsByCriteria constructor(private val eventRepository: EventRepository) : SingleUseCase<EventOverview, Search?>() {

   override suspend fun buildUseCaseObservable(param: Search?): Result<EventOverview> {
      return eventRepository.getEventsByCriteria(param)

   }
}