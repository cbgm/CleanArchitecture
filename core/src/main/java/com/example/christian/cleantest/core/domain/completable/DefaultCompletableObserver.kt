package com.example.christian.cleantest.core.domain.completable

import com.example.christian.cleantest.core.domain.base.BaseObserver

open class DefaultCompletableObserver : BaseObserver<Unit>() {
   override fun onError(throwable: Throwable) {
      //implemented in child
   }

   override fun onComplete() {
      //implemented in child
   }

   final override fun onSuccess(value: Unit) {
      //not needed
   }
}