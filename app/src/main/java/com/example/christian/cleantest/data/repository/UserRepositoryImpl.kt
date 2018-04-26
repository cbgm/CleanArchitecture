package com.example.christian.cleantest.data.repository

import com.example.christian.cleantest.device.NetManager
import com.example.christian.cleantest.data.model.UserDto
import com.example.christian.cleantest.domain.model.UserOverview
import com.example.christian.cleantest.domain.repository.UserRepository
import com.example.christian.cleantest.data.repository.remote.RemoteRepo
import com.example.christian.cleantest.data.repository.local.LocalRepo
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
        private val netManager: NetManager,
        private val remoteRepo: RemoteRepo,
        private val localRepo: LocalRepo
): UserRepository {

    override fun getAllUsers(): Single<UserOverview> {
        netManager.isConnected.let {
            if(it)
                return remoteRepo.getUsers().flatMap {
                    return@flatMap localRepo.saveUsers(it)
                            .toSingleDefault(it)
                }
            else
                return localRepo.getUsers()
        }
    }

    override fun saveUser(user: UserDto): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}