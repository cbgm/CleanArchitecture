package com.example.christian.cleantest.cart.data.repository.remote.cart

import com.example.christian.cleantest.cart.data.mapper.CartDtoMapper
import com.example.christian.cleantest.cart.domain.model.Cart
import io.reactivex.Single

class CartFromNetwork constructor(private val cartApi: CartApi) {

    fun getCart(userId: String) : Single<Cart> {


        return cartApi.getCartByUser(userId).map {
            CartDtoMapper.transform(it)
        }
    }

}