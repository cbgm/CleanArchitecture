package com.distribution.christian.cleantest.core.data.repository.remote

import com.distribution.christian.cleantest.core.data.model.UserDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApi {

   @GET("authenticate")
   fun authenticateUser(): Deferred<Response<UserDto>>

   @PUT("authenticate")
   fun updateUser(@Body eventDto: UserDto): Deferred<Response<UserDto>>
}