package com.example.christian.cleantest.presentation.cartview

import com.example.christian.cleantest.domain.model.Cart
import com.example.christian.cleantest.domain.usecases.GetCartByUser
import com.example.christian.cleantest.presentation.cartview.mapper.CartDomainMapper
import io.reactivex.observers.DisposableSingleObserver

class CartPresenter constructor(
        private val getCartByUser: GetCartByUser
): CartContract.Presenter{

    lateinit var cartview: CartContract.View

    inner class GetCartObserver: DisposableSingleObserver<Cart>() {
        override fun onSuccess(t: Cart) {
            cartview.showError(false)
            cartview.showContent(true)
            cartview.showLoading(false)
            cartview.initCart(CartDomainMapper.transform(t))
        }

        override fun onError(e: Throwable) {
            cartview.showError(true)
            cartview.showLoading(false)
            cartview.showContent(false)
        }

    }

    override fun loadCart(byUser: String) {
        getCartByUser.execute(GetCartObserver(), byUser)
    }

    override fun setVIew(v: CartContract.View) {
        cartview = v
    }

    override fun onBind() {
        cartview.showLoading(true)
        cartview.showError(false)
        cartview.showContent(false)
    }

    override fun onUnbind() {
        getCartByUser.dispose()
    }

}