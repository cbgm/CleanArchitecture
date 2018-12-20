package com.example.christian.cleantest.core.domain.completable

import com.example.christian.cleantest.core.domain.base.BaseObserver

open class DefaultCompletableObserver : BaseObserver<Unit>() {
   override fun onError(throwable: Throwable) {}
   override fun onComplete() {}
   final override fun onSuccess(value: Unit) {}
}