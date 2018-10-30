package com.example.christian.cleantest.cart.domain.repository

import com.example.christian.cleantest.core.data.model.UserDto
import com.example.christian.cleantest.cart.domain.model.UserOverview
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun getAllUsers(): Single<UserOverview>

    fun saveUser(user: UserDto): Completable
}