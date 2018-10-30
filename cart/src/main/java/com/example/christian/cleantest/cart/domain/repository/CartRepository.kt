package com.example.christian.cleantest.cart.domain.repository

import com.example.christian.cleantest.cart.domain.model.Cart
import io.reactivex.Single

interface CartRepository {

    fun getCart(userId: String): Single<Cart>
}