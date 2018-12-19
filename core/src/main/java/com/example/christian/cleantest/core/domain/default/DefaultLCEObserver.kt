package com.example.christian.cleantest.core.domain.default

import com.example.christian.cleantest.core.core.ui.BaseView

abstract class DefaultLCEObserver<T>(private val view: BaseView): DefaultObserver<T>() {
   override fun onSuccess(value: T) {
      super.onSuccess(value)
      view.showContent()
   }

   override fun onError(throwable: Throwable) {
      view.showError()
   }
}