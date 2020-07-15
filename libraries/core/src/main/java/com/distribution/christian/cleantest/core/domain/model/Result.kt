@file:Suppress("UNCHECKED_CAST")

package com.distribution.christian.cleantest.core.domain.model

import com.distribution.christian.cleantest.core.domain.base.BaseObserver


sealed class Result<out T : Any> {
   data class Success<out T : Any>(val data: T) : Result<T>()
   data class Complete(val none: Unit? = null) : Result<Nothing>()
   data class Error(val exception: Throwable?) : Result<Nothing>()
}

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
   if (this is Result.Success) action(data)

   return this
}

inline fun <T : Any> Result<T>.onComplete(action: () -> Unit): Result<T> {
   if (this is Result.Complete) action()

   return this
}

inline fun <T : Any> Result<T>.onError(action: (Throwable) -> Unit) {
   if (this is Result.Error && exception != null) action(exception)
}

fun <T> Result<Any>.emit(observer: BaseObserver<T>?){
   this.onSuccess { observer?.onSuccess(it as T) }
   this.onComplete { observer?.onComplete() }
   this.onError { observer?.onError(it) }
}


inline fun <T : Any, R : Any> Result<T>.mapOnSuccess(map: (T) -> R) = when (this) {
   is Result.Success -> Result.Success(map(data))
   is Result.Complete -> this
   is Result.Error -> this
}