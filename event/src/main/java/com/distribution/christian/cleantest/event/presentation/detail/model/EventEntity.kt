package com.distribution.christian.cleantest.event.presentation.detail.model

import java.io.Serializable


data class EventEntity(
      val id: Int,
      val name: String,
      val city: String,
      val location: String,
      val date: String,
      val time: String,
      val price: String,
      val description: String,
      val isStarred: Boolean = false
): Serializable