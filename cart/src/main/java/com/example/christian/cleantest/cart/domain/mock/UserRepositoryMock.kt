package com.example.christian.cleantest.cart.domain.mock

import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.cart.domain.repository.UserRepository
import com.example.christian.cleantest.core.data.model.UserDto
import com.example.christian.cleantest.core.domain.model.Result

class UserRepositoryMock constructor(
      private val userOverview: UserOverview
      ) : UserRepository {

   companion object {

      val userOverview = UserOverview(
            2,
            arrayListOf(User("Test1", "Nach1"), User("Test2", "Nach2"))
      )

      fun instance(): UserRepository {
         return UserRepositoryMock(userOverview)
      }
   }

   override suspend fun getAllUsers(): Result<UserOverview> {
      return Result.Success(userOverview)
   }


   override suspend fun saveUser(user: UserDto): Result<Nothing> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }
}