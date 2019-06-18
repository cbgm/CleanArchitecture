package com.distribution.christian.cleantest.core.data.repository

import com.distribution.christian.cleantest.core.data.repository.remote.AuthenticationFromNetwork
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
      private val authenticationFromNetwork: AuthenticationFromNetwork
): AuthenticationRepository {

   override suspend fun getAuthenticatedUser(): Result<User> {
      return authenticationFromNetwork.getAuthenticatedUser()
   }

   override suspend fun registerUser(email: String, password: String): Result<Nothing> {
      return authenticationFromNetwork.registerNewUser(email, password)
   }

   override suspend fun resetUserByMail(email: String): Result<Nothing> {
      return authenticationFromNetwork.resetUser(email)
   }

   override suspend fun loginUser(email: String, password: String): Result<User> {
      return authenticationFromNetwork.loginUser(email,password)
   }

   override suspend fun logoutUser(): Result<Nothing> {
      return authenticationFromNetwork.logoutUser()
   }
}