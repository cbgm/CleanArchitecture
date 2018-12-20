package com.example.christian.cleantest.cart.domain.repository

import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.core.domain.model.Result

interface CartRepository {

   suspend fun getCart(userId: String): Result<Cart>
}