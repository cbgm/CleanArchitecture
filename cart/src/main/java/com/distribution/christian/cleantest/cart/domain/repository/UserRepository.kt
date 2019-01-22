package com.distribution.christian.cleantest.cart.domain.repository

import com.distribution.christian.cleantest.core.data.model.UserDto
import com.distribution.christian.cleantest.cart.domain.model.UserOverview
import com.distribution.christian.cleantest.core.domain.model.Result

interface UserRepository {

    suspend fun getAllUsers(): Result<UserOverview>

    suspend fun saveUser(user: UserDto): Result<Nothing>
}