package com.distribution.christian.cleantest.cart.domain.repository

import com.distribution.christian.cleantest.cart.domain.model.Cart
import com.distribution.christian.cleantest.core.domain.model.Result

interface CartRepository {

   suspend fun getCart(userId: String): Result<Cart>
}