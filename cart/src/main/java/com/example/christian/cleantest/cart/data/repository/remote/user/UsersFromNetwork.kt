package com.example.christian.cleantest.cart.data.repository.remote.user

import com.example.christian.cleantest.cart.data.mapper.UserDtoMapper
import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.core.core.util.extension.mapToResult
import com.example.christian.cleantest.core.domain.model.Result

class UsersFromNetwork constructor(
      private val userApi: UserApi,
      private val userDtoMapper: UserDtoMapper
) {

   suspend fun getUsers(): Result<UserOverview> {
      val response = userApi.getAllUsers()
            .await()

      return response.mapToResult {
         UserOverview(
               10,
               (response.body()!!.map { user ->
                  userDtoMapper.transform(
                        user
                  )
               } as ArrayList<User>))
      }
   }
}