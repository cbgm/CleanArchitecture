package com.example.christian.cleantest.presentation.cartview

import com.example.christian.cleantest.core.ui.BasePresenter
import com.example.christian.cleantest.core.ui.BaseView
import com.example.christian.cleantest.presentation.cartview.model.CartEntity

interface CartContract {

    interface View: BaseView {
        fun initCart(cartEntity: CartEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun loadCart(byUser: String)
    }
}