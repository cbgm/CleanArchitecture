package com.distribution.christian.cleantest.event.data.cache

import org.koin.core.KoinComponent
import org.koin.core.inject
import javax.inject.Inject


class EventCache @Inject constructor(): KoinComponent {

   private val db:EventDatabase by inject()

    fun getEventDao(): EventRoomDao {
        return db.eventDao()
    }
}