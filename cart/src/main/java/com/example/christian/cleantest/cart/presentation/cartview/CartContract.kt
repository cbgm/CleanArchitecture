package com.example.christian.cleantest.cart.presentation.cartview

import com.example.christian.cleantest.cart.core.ui.BasePresenter
import com.example.christian.cleantest.cart.core.ui.BaseView
import com.example.christian.cleantest.cart.presentation.cartview.model.CartEntity

interface CartContract {

    interface View: BaseView {
        fun initCart(cartEntity: CartEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun loadCart(byUser: String)
    }
}