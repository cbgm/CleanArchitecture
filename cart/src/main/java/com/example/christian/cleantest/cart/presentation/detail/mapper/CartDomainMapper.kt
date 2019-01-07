package com.example.christian.cleantest.cart.presentation.detail.mapper

import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.cart.presentation.detail.model.CartEntity


class CartDomainMapper {

   fun transform(cart: Cart): CartEntity {
      return CartEntity(
            cart.price,
            cart.items
      )
   }
}