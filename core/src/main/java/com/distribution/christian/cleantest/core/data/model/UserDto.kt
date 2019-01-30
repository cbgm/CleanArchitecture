package com.distribution.christian.cleantest.core.data.model

data class UserDto(val firstName: String, val lastName: String, val alias: String, val email: String, val birthDate: String, val password: String, val isUpgraded: Boolean = false)