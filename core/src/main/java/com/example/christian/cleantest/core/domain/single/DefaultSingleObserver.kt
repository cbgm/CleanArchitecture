package com.example.christian.cleantest.core.domain.single

import io.reactivex.observers.DisposableSingleObserver

open class DefaultSingleObserver<T> : DisposableSingleObserver<T>() {
   override fun onSuccess(value: T) {
   }

   override fun onError(throwable: Throwable) {
   }
}