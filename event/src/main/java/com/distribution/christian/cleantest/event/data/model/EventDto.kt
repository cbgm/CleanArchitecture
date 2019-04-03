package com.distribution.christian.cleantest.event.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class EventDto(
      @PrimaryKey
      @NonNull
      @ColumnInfo(name = "id")
      val id: Int,
      @ColumnInfo(name = "name")
      val name: String,
      @ColumnInfo(name = "city")
      val city: String,
      @ColumnInfo(name = "location")
      val location: String,
      @ColumnInfo(name = "date")
      val date: String,
      @ColumnInfo(name = "time")
      val time: String,
      @ColumnInfo(name = "price")
      val price: String,
      @ColumnInfo(name = "description")
      val description: String,
      @ColumnInfo(name = "isStarred")
      var isStarred: Boolean = false
)