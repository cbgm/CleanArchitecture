package com.distribution.christian.cleantest.cart.data.repository

import com.distribution.christian.cleantest.cart.data.repository.remote.cart.CartFromNetwork
import com.distribution.christian.cleantest.cart.domain.model.Cart
import com.distribution.christian.cleantest.cart.domain.repository.CartRepository
import com.distribution.christian.cleantest.core.domain.model.Result

class CartRepositoryImpl constructor(
        private val cartFromNetwork: CartFromNetwork
): CartRepository {

    override suspend fun getCart(userId: String): Result<Cart> {
        return cartFromNetwork.getCart(userId)
    }
}