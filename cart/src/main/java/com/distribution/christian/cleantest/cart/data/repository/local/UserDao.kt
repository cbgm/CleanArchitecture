package com.distribution.christian.cleantest.cart.data.repository.local

import com.distribution.christian.cleantest.cart.domain.model.UserOverview
import io.reactivex.Completable
import io.reactivex.Single

interface UserDao {
    fun getUsers(): Single<UserOverview>

    fun saveUsers(data: UserOverview) : Completable
}