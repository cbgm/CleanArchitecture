package com.distribution.christian.cleantest.core.domain.model

data class User(val firstName: String, val lastName: String, val alias: String, val email: String, val password: String, val birthDate: String, val isUpgraded: Boolean = false)