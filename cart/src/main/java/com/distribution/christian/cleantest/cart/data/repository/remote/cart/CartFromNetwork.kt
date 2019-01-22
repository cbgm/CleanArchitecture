package com.distribution.christian.cleantest.cart.data.repository.remote.cart

import com.distribution.christian.cleantest.cart.data.mapper.CartDtoMapper
import com.distribution.christian.cleantest.cart.domain.model.Cart
import com.distribution.christian.cleantest.core.core.util.extension.mapToResult
import com.distribution.christian.cleantest.core.domain.model.Result

class CartFromNetwork constructor(private val cartApi: CartApi) {

   suspend fun getCart(userId: String): Result<Cart> {
      val response = cartApi.getCartByUser(userId)
            .await()

      return response.mapToResult { CartDtoMapper.transform(response.body()!!) }
   }
}