package com.distribution.christian.cleantest.event.presentation.overview.mapper

import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity

class EventOverviewDomainMapper {

    companion object {
        fun transform(eventOverview: EventOverview) : EventOverviewEntity {
            return EventOverviewEntity(
                  eventOverview.count,
                  eventOverview
                        .events
                        .flatMap {
                            listOf(
                                  transform(
                                        it
                                  )
                            )
                        }.toCollection(
                              ArrayList()
                        )
            )
        }

        private fun transform(event: Event) : EventEntity {
            return EventEntity(
                  event.id,
                  event.name,
                  event.city,
                  event.location,
                  event.date,
                  event.time,
                  event.price,
                  event.description,
                  event.isStarred
            )
        }
    }
}