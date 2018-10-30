package com.example.christian.cleantest.domain.usecases

import com.example.christian.cleantest.domain.model.Cart
import com.example.christian.cleantest.domain.repository.CartRepository
import io.reactivex.Single

class GetCartByUser constructor(private val cartRepository: CartRepository): SingleUseCase<Cart, String>() {

    override fun buildUseCaseObservable(param: String): Single<Cart> {
        return this.cartRepository.getCart(param)
    }

}