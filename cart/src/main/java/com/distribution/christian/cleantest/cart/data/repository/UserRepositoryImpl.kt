package com.distribution.christian.cleantest.cart.data.repository

import com.distribution.christian.cleantest.core.device.NetManager
import com.distribution.christian.cleantest.core.data.model.UserDto
import com.distribution.christian.cleantest.cart.domain.model.UserOverview
import com.distribution.christian.cleantest.cart.domain.repository.UserRepository
import com.distribution.christian.cleantest.cart.data.repository.remote.user.UsersFromNetwork
import com.distribution.christian.cleantest.cart.data.repository.local.UsersFromLocal
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.onSuccess

class UserRepositoryImpl constructor(
      private val netManager: NetManager,
      private val usersFromNetwork: UsersFromNetwork,
      private val usersFromLocal: UsersFromLocal
) : UserRepository {

   override suspend fun getAllUsers(): Result<UserOverview> {
      //for different purposes
      /*netManager.isConnected.let {
          if(it)
              return usersFromNetwork.getUsers().flatMap {
                  return@flatMap usersFromLocal.saveUsers(it)
                          .toSingleDefault(it)
              }
          else
              return usersFromLocal.getUsers()
      }*/
      //if no network, local cached data is loaded(interceptor)
      val result = usersFromNetwork.getUsers()
      result.onSuccess { usersFromLocal.saveUsers(it) }
      return result
   }


   override suspend fun saveUser(user: UserDto): Result<Nothing> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }
}