package com.distribution.christian.cleantest.core.domain.single

import com.distribution.christian.cleantest.core.core.ui.BaseView


abstract class SingleLCEObserver<T>(private val view: BaseView): DefaultSingleObserver<T>() {
   override fun onSuccess(value: T) {
      super.onSuccess(value)
      view.showContent()
   }

   override fun onError(throwable: Throwable) {
      view.showError()
   }
}