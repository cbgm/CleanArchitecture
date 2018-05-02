package com.example.christian.cleantest.data.repository.remote.cart

import com.example.christian.cleantest.data.model.CartDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CartApi {

    @GET("cart")
    fun getCartByUser(@Query("userId")userId: String): Single<CartDto>
}