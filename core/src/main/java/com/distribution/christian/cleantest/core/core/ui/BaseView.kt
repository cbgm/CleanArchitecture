package com.distribution.christian.cleantest.core.core.ui

interface BaseView {
    fun showError(isVisible: Boolean)
    fun showLoading(isVisible: Boolean)
    fun showContent(isVisible: Boolean)

    fun showLoading() {
        showLoading(true)
        showError(false)
        showContent(false)
    }

    fun showError() {
        showLoading(false)
        showError(true)
        showContent(false)
    }

    fun showContent() {
        showLoading(false)
        showError(false)
        showContent(true)
    }
}