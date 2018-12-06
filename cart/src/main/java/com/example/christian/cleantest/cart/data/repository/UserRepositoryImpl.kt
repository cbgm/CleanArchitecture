package com.example.christian.cleantest.cart.data.repository

import com.example.christian.cleantest.core.device.NetManager
import com.example.christian.cleantest.core.data.model.UserDto
import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.cart.domain.repository.UserRepository
import com.example.christian.cleantest.cart.data.repository.remote.user.UsersFromNetwork
import com.example.christian.cleantest.cart.data.repository.local.UsersFromLocal
import io.reactivex.Completable
import io.reactivex.Single

class UserRepositoryImpl constructor(
      private val netManager: NetManager,
      private val usersFromNetwork: UsersFromNetwork,
      private val usersFromLocal: UsersFromLocal
): UserRepository {

    override fun getAllUsers(): Single<UserOverview> {
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
       return usersFromNetwork.getUsers().flatMap {
          return@flatMap usersFromLocal.saveUsers(it)
                .toSingleDefault(it)
       }
    }

    override fun saveUser(user: UserDto): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}