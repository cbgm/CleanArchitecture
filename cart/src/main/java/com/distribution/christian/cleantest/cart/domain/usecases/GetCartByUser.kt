package com.distribution.christian.cleantest.cart.domain.usecases

import com.distribution.christian.cleantest.cart.domain.model.Cart
import com.distribution.christian.cleantest.cart.domain.repository.CartRepository
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.core.domain.model.Result

class GetCartByUser constructor(private val cartRepository: CartRepository) : SingleUseCase<Cart, String>() {

   override suspend fun buildUseCaseObservable(param: String): Result<Cart> {
      return cartRepository.getCart(param)
   }


}