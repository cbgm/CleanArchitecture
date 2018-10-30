package com.example.christian.cleantest.data.repository

import com.example.christian.cleantest.data.repository.remote.cart.CartFromNetwork
import com.example.christian.cleantest.domain.model.Cart
import com.example.christian.cleantest.domain.repository.CartRepository
import io.reactivex.Single

class CartRepositoryImpl constructor(
        private val cartFromNetwork: CartFromNetwork
): CartRepository {

    override fun getCart(userId: String): Single<Cart> {
        return cartFromNetwork.getCart(userId)
    }
}