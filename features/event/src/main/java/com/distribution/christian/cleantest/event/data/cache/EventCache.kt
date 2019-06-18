package com.distribution.christian.cleantest.event.data.cache

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import javax.inject.Inject


class EventCache @Inject constructor():KoinComponent {

   private val db:EventDatabase by inject()

    fun getEventDao(): EventRoomDao {
        return db.eventDao()
    }
}