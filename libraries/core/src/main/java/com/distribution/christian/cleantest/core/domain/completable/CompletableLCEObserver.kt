package com.distribution.christian.cleantest.core.domain.completable

import com.distribution.christian.cleantest.core.core.ui.BaseView


abstract class CompletableLCEObserver(private val view: BaseView) : DefaultCompletableObserver() {
   override fun onComplete() {
      super.onComplete()
      view.showContent()
   }

   override fun onError(throwable: Throwable) {
      super.onError(throwable)
      view.showError()
   }
}