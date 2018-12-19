package com.example.christian.cleantest.core.domain.default

open class DefaultObserver<T> {
   open fun onSuccess(value: T) {}

   open fun onError(throwable: Throwable) {}
}