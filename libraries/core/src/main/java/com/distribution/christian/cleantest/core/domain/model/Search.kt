package com.distribution.christian.cleantest.core.domain.model


data class Search(
      val userId: String,
      val city: String,
      val type: String,
      val distance: Int,
      val maxPrice: Int
)