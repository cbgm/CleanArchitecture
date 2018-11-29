package com.example.christian.cleantest.cart.presentation.cartview

import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.cart.domain.usecases.GetCartByUser
import com.example.christian.cleantest.cart.presentation.cartview.mapper.CartDomainMapper
import io.reactivex.observers.DisposableSingleObserver

class DetailPresenter constructor(
        private val getCartByUser: GetCartByUser
): DetailContract.Presenter {

    lateinit var cartview: DetailContract.View

    inner class GetCartObserver: DisposableSingleObserver<Cart>() {
        override fun onSuccess(t: Cart) {
            cartview.showContent()
            cartview.initCart(CartDomainMapper.transform(t))
        }

        override fun onError(e: Throwable) {
            cartview.showError()
        }

    }

    override fun loadCart(byUser: String) {
        getCartByUser.execute(GetCartObserver(), byUser)
    }

    override fun setVIew(v: DetailContract.View) {
        cartview = v
    }

    override fun onBind() {
        cartview.showLoading()
    }

    override fun onUnbind() {
        getCartByUser.dispose()
    }

}