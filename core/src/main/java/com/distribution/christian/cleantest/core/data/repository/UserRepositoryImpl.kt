package com.distribution.christian.cleantest.core.data.repository

import com.distribution.christian.cleantest.core.data.repository.remote.UserFromNetwork
import com.distribution.christian.cleantest.core.device.NetManager
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.repository.UserRepository

class UserRepositoryImpl constructor(
      private val netManager: NetManager,
      private val userFromNetwork: UserFromNetwork
) : UserRepository {

   override suspend fun updateActiveUser(user: User): Result<User> {
      return userFromNetwork.updateUser(user)
   }

   override suspend fun getActiveUser(): Result<User> {
      return userFromNetwork.getUser()
   }
}