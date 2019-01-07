package com.example.christian.cleantest.cart.data.mapper

import com.example.christian.cleantest.cart.data.model.CartDto
import com.example.christian.cleantest.cart.domain.model.Cart

class CartDtoMapper {

   fun transform(cart: CartDto): Cart {
      return Cart(cart.price, cart.items)
   }
}