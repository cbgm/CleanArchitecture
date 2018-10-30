package com.example.christian.cleantest.cart.core.ui

interface BasePresenter<in V: BaseView> {

    fun setVIew(v: V)
    fun onBind()
    fun onUnbind()
}