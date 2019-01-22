package com.distribution.christian.cleantest.cart.data.mapper

import com.distribution.christian.cleantest.cart.data.model.CartDto
import com.distribution.christian.cleantest.cart.domain.model.Cart

abstract class CartDtoMapper {

    companion object {
        fun transform(cart: CartDto): Cart {
            return Cart(cart.price, cart.items)
        }
    }
}