package com.example.christian.cleantest.data.repository.remote.cart

import com.example.christian.cleantest.data.mapper.CartDtoMapper
import com.example.christian.cleantest.domain.model.Cart
import io.reactivex.Single
import javax.inject.Inject

class CartFromNetwork @Inject constructor(private val cartApi: CartApi) {

    fun getCart(userId: String) : Single<Cart> {


        return cartApi.getCartByUser(userId).map {
            CartDtoMapper.transform(it)
        }
    }

}