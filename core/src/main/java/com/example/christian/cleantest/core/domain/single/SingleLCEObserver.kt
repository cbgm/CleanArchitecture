package com.example.christian.cleantest.core.domain.single

import com.example.christian.cleantest.core.core.ui.BaseView
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleLCEObserver<T>(private val view: BaseView): DisposableSingleObserver<T>() {
   override fun onSuccess(value: T) {
      view.showContent()
   }

   override fun onError(throwable: Throwable) {
      view.showError()
   }
}