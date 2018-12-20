package com.example.christian.cleantest.core.domain.single

import com.example.christian.cleantest.core.domain.base.BaseObserver

open class DefaultSingleObserver<T> : BaseObserver<T>() {
   override fun onSuccess(value: T) {
      //implemented in child
   }

   override fun onError(throwable: Throwable) {
      //implemented in child
   }

   final override fun onComplete() {
      //not needed
   }
}