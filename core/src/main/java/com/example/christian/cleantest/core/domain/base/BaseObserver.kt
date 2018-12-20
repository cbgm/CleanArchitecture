package com.example.christian.cleantest.core.domain.base

abstract class BaseObserver<Any> {
   open fun onSuccess(value:Any) {}
   open fun onError(throwable: Throwable) {}
   open fun onComplete() {}
}