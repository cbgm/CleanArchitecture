package com.distribution.christian.cleantest.core.core.ui


interface BasePresenter<in V: BaseView> {

    fun setVIew(view: V)

    fun onBind()

    fun onUnbind()
}