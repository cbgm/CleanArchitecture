package com.distribution.christian.cleantest.cart.presentation.detail

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView
import com.distribution.christian.cleantest.cart.presentation.detail.model.CartEntity

interface DetailContract {

    interface View: BaseView {
        fun initCart(cartEntity: CartEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun loadCart(byUser: String)
    }
}