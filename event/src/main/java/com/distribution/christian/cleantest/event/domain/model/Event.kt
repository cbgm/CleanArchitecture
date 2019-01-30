package com.distribution.christian.cleantest.event.domain.model

data class Event(
      val id: Int,
      val name: String,
      val city: String,
      val location: String,
      val date: String,
      val time: String,
      val price: String,
      val description: String,
      val isStarred: Boolean = false
)