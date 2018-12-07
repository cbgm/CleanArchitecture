package com.example.christian.cleantest.cart.data.repository.remote.user

import com.example.christian.cleantest.cart.data.mapper.UserDtoMapper
import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.cart.domain.model.UserOverview
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class UsersFromNetwork constructor(private val userApi: UserApi) {

   fun getUsers(): Single<UserOverview> {

      return userApi.getAllUsers()
            .flatMap {
               Single.just(
                     UserOverview(
                           10,
                           (it.map { user ->
                              UserDtoMapper.transform(
                                    user
                              )
                           } as ArrayList<User>)
                     )
               )
                     .delay(1, TimeUnit.SECONDS)
            }
   }
}