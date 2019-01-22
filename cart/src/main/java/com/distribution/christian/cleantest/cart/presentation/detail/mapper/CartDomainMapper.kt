package com.distribution.christian.cleantest.cart.presentation.detail.mapper

import com.distribution.christian.cleantest.cart.domain.model.Cart
import com.distribution.christian.cleantest.cart.presentation.detail.model.CartEntity


abstract class CartDomainMapper {

    companion object {
        fun transform(cart: Cart) : CartEntity {
            return CartEntity(
                  cart.price,
                  cart.items
            )
        }
    }
}