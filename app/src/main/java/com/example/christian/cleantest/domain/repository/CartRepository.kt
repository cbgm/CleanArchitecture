package com.example.christian.cleantest.domain.repository

import com.example.christian.cleantest.domain.model.Cart
import io.reactivex.Single

interface CartRepository {

    fun getCart(userId: String): Single<Cart>
}