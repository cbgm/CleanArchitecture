package com.distribution.christian.cleantest.cart.data.repository.remote.user

import com.distribution.christian.cleantest.cart.data.mapper.UserDtoMapper
import com.distribution.christian.cleantest.cart.domain.model.User
import com.distribution.christian.cleantest.cart.domain.model.UserOverview
import com.distribution.christian.cleantest.core.core.util.extension.mapToResult
import com.distribution.christian.cleantest.core.domain.model.Result

class UsersFromNetwork constructor(private val userApi: UserApi) {

   suspend fun getUsers(): Result<UserOverview> {
      val response = userApi.getAllUsers()
            .await()

      return response.mapToResult {
         UserOverview(
               10,
               (response.body()!!.map { user ->
                  UserDtoMapper.transform(
                        user
                  )
               } as ArrayList<User>))
      }
   }
}