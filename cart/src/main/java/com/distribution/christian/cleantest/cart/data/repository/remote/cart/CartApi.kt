package com.distribution.christian.cleantest.cart.data.repository.remote.cart

import com.distribution.christian.cleantest.cart.data.model.CartDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CartApi {

    @GET("cart")
    fun getCartByUser(@Query("userId")userId: String): Deferred<Response<CartDto>>
}