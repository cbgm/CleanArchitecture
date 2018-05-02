package com.example.christian.cleantest.presentation.cartview.mapper

import com.example.christian.cleantest.domain.model.Cart
import com.example.christian.cleantest.presentation.cartview.model.CartEntity


abstract class CartDomainMapper {

    companion object {
        fun transform(cart: Cart) : CartEntity{
            return CartEntity(cart.price, cart.items)
        }
    }
}