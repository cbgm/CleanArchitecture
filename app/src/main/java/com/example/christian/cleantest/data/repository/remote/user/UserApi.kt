package com.example.christian.cleantest.data.repository.remote.user

import com.example.christian.cleantest.data.model.UserDto
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getAllUsers(): Single<ArrayList<UserDto>>
}