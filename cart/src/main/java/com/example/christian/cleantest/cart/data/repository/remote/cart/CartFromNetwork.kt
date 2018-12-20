package com.example.christian.cleantest.cart.data.repository.remote.cart

import com.example.christian.cleantest.cart.data.mapper.CartDtoMapper
import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.core.core.util.extension.mapToResult
import com.example.christian.cleantest.core.domain.model.Result

class CartFromNetwork constructor(private val cartApi: CartApi) {

   suspend fun getCart(userId: String): Result<Cart> {
      val response = cartApi.getCartByUser(userId)
            .await()

      return response.mapToResult { CartDtoMapper.transform(response.body()!!) }
   }
}