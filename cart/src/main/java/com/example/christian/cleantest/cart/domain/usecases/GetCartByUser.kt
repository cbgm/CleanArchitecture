package com.example.christian.cleantest.cart.domain.usecases

import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.cart.domain.repository.CartRepository
import io.reactivex.Single

class GetCartByUser constructor(private val cartRepository: CartRepository): SingleUseCase<Cart, String>() {

    override fun buildUseCaseObservable(param: String): Single<Cart> {
        return this.cartRepository.getCart(param)
    }

}