package com.distribution.christian.cleantest.core.data.repository.remote

import com.distribution.christian.cleantest.core.core.util.extension.mapToResult
import com.distribution.christian.cleantest.core.data.mapper.UserDtoMapper
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.model.Result


class UserFromNetwork constructor(private val userApi: UserApi) {

   suspend fun getUser(): Result<User> {
      val response = userApi.authenticateUser()
            .await()

      return response.mapToResult {
         UserDtoMapper.transform(it)
      }
   }

   suspend fun updateUser(user: User): Result<User> {
      val response = userApi.updateUser(UserDtoMapper.transform(user))
            .await()
      return response.mapToResult { UserDtoMapper.transform(response.body()!!) }
   }
}