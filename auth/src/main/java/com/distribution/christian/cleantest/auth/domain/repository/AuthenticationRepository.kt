package com.distribution.christian.cleantest.auth.domain.repository

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.User

interface AuthenticationRepository {

   suspend fun getAuthenticatedUser(): Result<User>

   suspend fun registerUser(email: String, password: String): Result<Nothing>

   suspend fun resetUserByMail(email: String): Result<Nothing>
}