package com.distribution.christian.cleantest.cart.data.repository.local

import com.distribution.christian.cleantest.cart.domain.model.UserOverview
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UsersFromLocal @Inject constructor() {
    private val userDao = DaoFactory.getDaoFactory(
          DaoFactory.SQL
    )
          .getUserDao()

    fun getUsers(): Single<UserOverview> = userDao.getUsers()

    fun saveUsers(data: UserOverview) : Completable = userDao.saveUsers(data)
}