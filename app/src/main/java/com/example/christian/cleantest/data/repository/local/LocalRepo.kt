package com.example.christian.cleantest.data.repository.local

import com.example.christian.cleantest.domain.model.UserOverview
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalRepo @Inject constructor() {
    private val userDao = DaoFactory.getDaoFactory(DaoFactory.SQL).getUserDao()

    fun getUsers(): Single<UserOverview> = userDao.getUsers()

    fun saveUsers(data: UserOverview) : Completable = userDao.saveUsers(data)
}