package com.distribution.christian.cleantest.event.data.model


data class EventDto(
      val id: Int,
      val name: String,
      val city: String,
      val location: String,
      val date: String,
      val time: String,
      val price: String,
      val description: String,
      var isStarred: Boolean = false
)