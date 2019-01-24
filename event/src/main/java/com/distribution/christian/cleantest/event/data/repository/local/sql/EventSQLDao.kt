package com.distribution.christian.cleantest.event.data.repository.local.sql

import com.distribution.christian.cleantest.core.core.mock.EventGenerator
import com.distribution.christian.cleantest.core.data.model.EventDto
import com.distribution.christian.cleantest.event.data.repository.local.EventDao
import com.distribution.christian.cleantest.event.data.mapper.EventDtoMapper
import com.distribution.christian.cleantest.core.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class EventSQLDao : EventDao {


   private var eventDto: EventDto = EventDto(
         2,
         "Wild PartyHell in Buku",
         "Burgkunstadt",
         "Cityhall, Street 12",
         "Thursday 13.09.2017",
         "1pm - 5pm",
         "13 Euro",
         "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
   )


   override fun getEvents(): Single<EventOverview> {
      val data = ArrayList<EventDto>()
      /*data.add(
            eventDto
      )
      data.add(
            eventDto
      )*/
      data.addAll(EventGenerator.generate(2))
      return Single.just(
            EventOverview(
                  10,
                  (data.map { event ->
                     EventDtoMapper.transform(
                           event
                     )
                  } as ArrayList<Event>)
            )
      )
            .delay(1, TimeUnit.SECONDS)
   }


   override fun saveEvents(data: EventOverview): Completable {
      return Single.just(1)
            .delay(1, TimeUnit.SECONDS)
            .ignoreElement()
   }

   override fun getEventById(eventId: String): Single<Event> {
      return Single.just(
            EventDtoMapper.transform(eventDto)
      )
   }
}