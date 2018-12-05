package com.example.christian.cleantest.cart.presentation.detail

import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.cart.domain.usecases.GetCartByUser
import com.example.christian.cleantest.cart.presentation.detail.mapper.CartDomainMapper
import com.example.christian.cleantest.core.domain.single.SingleLCEObserver

class DetailPresenter constructor(
      private val getCartByUser: GetCartByUser
) : DetailContract.Presenter {

   lateinit var cartview: DetailContract.View

   inner class GetCartObserver : SingleLCEObserver<Cart>(cartview) {
      override fun onSuccess(t: Cart) {
         super.onSuccess(t)
         cartview.initCart(CartDomainMapper.transform(t))
      }

      override fun onError(e: Throwable) {
         super.onError(e)
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