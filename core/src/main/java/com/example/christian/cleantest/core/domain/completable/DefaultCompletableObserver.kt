package com.example.christian.cleantest.core.domain.completable

open class DefaultCompletableObserver {

   open fun onError(throwable: Throwable) {
   }

   open fun onComplete() {}
}