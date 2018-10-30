package com.example.christian.cleantest.cart.data.repository

import com.example.christian.cleantest.cart.data.repository.remote.cart.CartFromNetwork
import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.cart.domain.repository.CartRepository
import io.reactivex.Single

class CartRepositoryImpl constructor(
        private val cartFromNetwork: CartFromNetwork
): CartRepository {

    override fun getCart(userId: String): Single<Cart> {
        return cartFromNetwork.getCart(userId)
    }
}