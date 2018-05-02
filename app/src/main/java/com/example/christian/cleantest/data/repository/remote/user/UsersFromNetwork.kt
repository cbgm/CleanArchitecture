package com.example.christian.cleantest.data.repository.remote.user

import com.example.christian.cleantest.data.mapper.UserDtoMapper
import com.example.christian.cleantest.data.repository.remote.user.UserApi
import com.example.christian.cleantest.domain.model.UserOverview
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UsersFromNetwork @Inject constructor(private val userApi: UserApi) {

    fun getUsers() : Single<UserOverview> {

        return userApi.getAllUsers()
                .flatMap {
                    Single.just(
                            UserOverview(10, UserDtoMapper.transform(it))
                    ).delay(1, TimeUnit.SECONDS)
                }
    }
}