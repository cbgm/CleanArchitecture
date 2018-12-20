package com.example.christian.cleantest.core.domain.single

import com.example.christian.cleantest.core.domain.base.BaseObserver

open class DefaultSingleObserver<T> : BaseObserver<T>() {
   override fun onSuccess(value: T) {}
   override fun onError(throwable: Throwable) {}
   final override fun onComplete() {}
}