package com.example.christian.cleantest.cart.data.repository.remote.user

import com.example.christian.cleantest.core.data.model.UserDto
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getAllUsers(): Single<List<UserDto>>
}