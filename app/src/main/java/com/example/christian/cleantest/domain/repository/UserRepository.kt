package com.example.christian.cleantest.domain.repository

import com.example.christian.cleantest.data.model.UserDto
import com.example.christian.cleantest.domain.model.UserOverview
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun getAllUsers(): Single<UserOverview>

    fun saveUser(user: UserDto): Completable
}