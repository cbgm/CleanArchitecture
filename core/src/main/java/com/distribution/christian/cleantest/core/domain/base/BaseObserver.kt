package com.distribution.christian.cleantest.core.domain.base

abstract class BaseObserver<Any> {
   open fun onSuccess(value: Any) {
      //implemented in child
   }

   open fun onError(throwable: Throwable) {
      //implemented in child
   }

   open fun onComplete() {
      //implemented in child
   }
}