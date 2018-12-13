package com.example.christian.cleantest.core.domain.completable

import io.reactivex.observers.DisposableCompletableObserver

open class DefaultCompletableObserver : DisposableCompletableObserver() {
   override fun onComplete() {
   }

   override fun onError(throwable: Throwable) {
   }
}