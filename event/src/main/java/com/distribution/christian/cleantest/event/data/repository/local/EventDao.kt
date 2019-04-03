package com.distribution.christian.cleantest.event.data.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.Search

@Dao
interface EventDao {

   @Query("SELECT * FROM event_table")
   fun getEventsByCriteria(search: Search?): Result<EventOverview>

   fun saveEvents(data: EventOverview): Result<Nothing>

   fun updateEvent(event: Event): Result<Event>

   fun getEventById(eventId: String): Result<Event>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun saveEvent(event: Event): Result<Event>

   @Delete
   fun deleteEvent(evntId: String): Result<Nothing>
}