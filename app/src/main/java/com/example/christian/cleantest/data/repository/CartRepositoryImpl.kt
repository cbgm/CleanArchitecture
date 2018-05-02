package com.example.christian.cleantest.data.repository

import com.example.christian.cleantest.data.repository.remote.cart.CartFromNetwork
import com.example.christian.cleantest.domain.model.Cart
import com.example.christian.cleantest.domain.repository.CartRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
        private val cartFromNetwork: CartFromNetwork
): CartRepository {

    override fun getCart(userId: String): Single<Cart> {
        return cartFromNetwork.getCart(userId)
    }
}