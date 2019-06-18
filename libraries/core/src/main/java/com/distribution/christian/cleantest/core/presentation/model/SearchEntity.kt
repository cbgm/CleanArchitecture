package com.distribution.christian.cleantest.core.presentation.model


data class SearchEntity(
      val userId: String,
      val city: String,
      val type: String,
      val distance: Int,
      val maxPrice: Int
)