package com.distribution.christian.cleantest.profile.domain.model


data class ProfileOverview(
      val name: String,
      val alias: String,
      val email: String,
      val password: String,
      val birthDate: String,
      val isUpgraded: Boolean = false,
      val city: String,
      val type: String,
      val distance: Int,
      val maxPrice: Int
)