package com.example.christian.cleantest.core.ui

interface BaseView {
    fun showError(visible: Boolean)
    fun showLoading(visible: Boolean)
    fun showContent(visible: Boolean)
}