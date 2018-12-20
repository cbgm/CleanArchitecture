package com.example.christian.cleantest.cart.data.repository

import com.example.christian.cleantest.cart.data.repository.remote.cart.CartFromNetwork
import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.cart.domain.repository.CartRepository
import com.example.christian.cleantest.core.domain.model.Result

class CartRepositoryImpl constructor(
        private val cartFromNetwork: CartFromNetwork
): CartRepository {

    override suspend fun getCart(userId: String): Result<Cart> {
        return cartFromNetwork.getCart(userId)
    }
}