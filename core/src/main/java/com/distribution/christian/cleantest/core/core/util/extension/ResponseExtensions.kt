package com.distribution.christian.cleantest.core.core.util.extension

import com.distribution.christian.cleantest.core.domain.model.Result
import retrofit2.Response

inline fun <T : Any, R : Any> Response<T>.mapToResult(map: (T) -> R): Result<R> {
   return if (this.isSuccessful) {
      when(code()) {
         204 -> Result.Complete()
         else -> Result.Success(map(this.body() as T))
      }
   } else {
      Result.Error(Throwable("" + this.errorBody()))
   }
}