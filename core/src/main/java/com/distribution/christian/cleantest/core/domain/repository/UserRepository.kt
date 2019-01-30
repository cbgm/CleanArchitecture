package com.distribution.christian.cleantest.core.domain.repository

import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.model.Result

interface UserRepository {

   suspend fun getActiveUser(): Result<User>
   suspend fun updateActiveUser(user: User): Result<User>
}