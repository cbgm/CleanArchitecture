package com.example.christian.cleantest.core.domain.single

open class DefaultSingleObserver<T> {
   open fun onSuccess(value: T) {}

   open fun onError(throwable: Throwable) {}
}