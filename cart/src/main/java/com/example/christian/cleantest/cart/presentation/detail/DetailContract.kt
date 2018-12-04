package com.example.christian.cleantest.cart.presentation.detail

import com.example.christian.cleantest.core.core.ui.BasePresenter
import com.example.christian.cleantest.core.core.ui.BaseView
import com.example.christian.cleantest.cart.presentation.detail.model.CartEntity

interface DetailContract {

    interface View: BaseView {
        fun initCart(cartEntity: CartEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun loadCart(byUser: String)
    }
}