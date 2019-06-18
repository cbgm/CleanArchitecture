package com.distribution.christian.cleantest.event.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.distribution.christian.cleantest.event.data.model.EventDto


@Dao
interface EventRoomDao {

   @Query("SELECT * FROM event_table")
   fun getEventsByCriteria(): List<EventDto>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun saveEvent(eventDto: EventDto)

   @Delete
   fun deleteEvent(eventDto: EventDto)
}