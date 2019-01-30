package com.distribution.christian.cleantest.profile.presentation.overview.model

data class ProfileOverviewEntity (val name: String, val alias: String, val email: String, val password: String, val birthDate: String, val isUpgraded: Boolean = false, val city: String, val type: String, val distance: Int, val maxPrice: Int)