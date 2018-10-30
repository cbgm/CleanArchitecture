package com.example.christian.cleantest.data.repository

import com.example.christian.cleantest.device.NetManager
import com.example.christian.cleantest.data.model.UserDto
import com.example.christian.cleantest.domain.model.UserOverview
import com.example.christian.cleantest.domain.repository.UserRepository
import com.example.christian.cleantest.data.repository.remote.user.UsersFromNetwork
import com.example.christian.cleantest.data.repository.local.UsersFromLocal
import io.reactivex.Completable
import io.reactivex.Single

class UserRepositoryImpl constructor(
        private val netManager: NetManager,
        private val usersFromNetwork: UsersFromNetwork,
        private val usersFromLocal: UsersFromLocal
): UserRepository {

    override fun getAllUsers(): Single<UserOverview> {
        netManager.isConnected.let {
            if(it)
                return usersFromNetwork.getUsers().flatMap {
                    return@flatMap usersFromLocal.saveUsers(it)
                            .toSingleDefault(it)
                }
            else
                return usersFromLocal.getUsers()
        }
    }

    override fun saveUser(user: UserDto): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}