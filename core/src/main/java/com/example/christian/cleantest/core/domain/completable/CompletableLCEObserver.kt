package com.example.christian.cleantest.core.domain.completable

import com.example.christian.cleantest.core.core.ui.BaseView
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableLCEObserver(private val view: BaseView): DisposableCompletableObserver() {
   override fun onComplete() {
      view.showContent()
   }

   override fun onError(throwable: Throwable) {
      view.showError()
   }
}