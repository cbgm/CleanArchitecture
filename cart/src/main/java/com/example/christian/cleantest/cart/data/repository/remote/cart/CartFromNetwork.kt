package com.example.christian.cleantest.cart.data.repository.remote.cart

import com.example.christian.cleantest.cart.data.mapper.CartDtoMapper
import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.core.domain.model.Result
import java.io.IOException

class CartFromNetwork constructor(private val cartApi: CartApi) {

   suspend fun getCart(userId: String): Result<Cart> {
      val response = cartApi.getCartByUser(userId)
            .await()

      if (response.isSuccessful) {
         return Result.Success(CartDtoMapper.transform(response.body()!!))
      }
      return Result.Error(IOException("" + response.errorBody()))
   }
}