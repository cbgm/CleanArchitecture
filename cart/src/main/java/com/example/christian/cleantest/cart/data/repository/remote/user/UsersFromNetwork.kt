package com.example.christian.cleantest.cart.data.repository.remote.user

import com.example.christian.cleantest.cart.data.mapper.UserDtoMapper
import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.core.domain.model.Result
import java.io.IOException

class UsersFromNetwork constructor(private val userApi: UserApi) {

   suspend fun getUsers(): Result<UserOverview> {
      val response = userApi.getAllUsers()
            .await()

      if (response.isSuccessful) {
         return Result.Success(UserOverview(
               10,
               (response.body()!!.map { user ->
                  UserDtoMapper.transform(
                        user
                  )
               } as ArrayList<User>)))
      }
      return Result.Error(IOException("" + response.errorBody()))
   }
}