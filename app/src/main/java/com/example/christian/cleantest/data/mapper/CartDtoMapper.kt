package com.example.christian.cleantest.data.mapper

import com.example.christian.cleantest.data.model.CartDto
import com.example.christian.cleantest.domain.model.Cart

abstract class CartDtoMapper {

    companion object {
        fun transform(cart: CartDto): Cart {
            return Cart(cart.price, cart.items)
        }
    }
}