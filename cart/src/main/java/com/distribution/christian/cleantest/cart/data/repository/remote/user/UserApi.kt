package com.distribution.christian.cleantest.cart.data.repository.remote.user

import com.distribution.christian.cleantest.core.data.model.UserDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getAllUsers(): Deferred<Response<List<UserDto>>>
}