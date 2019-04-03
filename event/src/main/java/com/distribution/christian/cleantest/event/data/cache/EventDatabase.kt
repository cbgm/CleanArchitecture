package com.distribution.christian.cleantest.event.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.distribution.christian.cleantest.event.data.model.EventDto

@Database(entities = [EventDto::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

   abstract fun eventDao(): EventRoomDao
}

