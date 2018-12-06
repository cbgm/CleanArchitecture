package com.example.christian.cleantest.cart.data.repository.local.sql

import com.example.christian.cleantest.cart.data.mapper.UserDtoMapper
import com.example.christian.cleantest.cart.data.repository.local.UserDao
import com.example.christian.cleantest.core.data.model.UserDto
import com.example.christian.cleantest.cart.domain.model.UserOverview
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class UserSQLDao: UserDao {
    override fun getUsers(): Single<UserOverview> {
        val data = ArrayList<UserDto>()
        data.add(UserDto("me", "bla"))
        data.add(UserDto("him", "blo"))
        return Single.just(
              UserOverview(
                    10,
                    UserDtoMapper.transform(
                          data
                    )
              )
        ).delay(1, TimeUnit.SECONDS)
    }


    override fun saveUsers(data: UserOverview) : Completable{
        return Single.just(1).delay(1, TimeUnit.SECONDS).ignoreElement()
    }
}