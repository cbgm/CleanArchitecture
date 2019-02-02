package com.distribution.christian.cleantest.core.domain.base

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.onError
import com.distribution.christian.cleantest.core.domain.model.onSuccess
import com.distribution.christian.cleantest.core.domain.model.onComplete
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Suppress("UNCHECKED_CAST")
abstract class BaseUseCase<T, in Params> {

   var job: Job? = null

   abstract suspend fun buildUseCaseObservable(param: Params): Result<Any>

   fun execute(observer: BaseObserver<T>? = null, param: Params) {
      dispose()
      job = CoroutineScope(Dispatchers.Main).launch {
         delay(1000)
         val result = buildUseCaseObservable(param)
         result.onSuccess { observer?.onSuccess(it as T) }
         result.onComplete{ observer?.onComplete()}
         result.onError { observer?.onError(it) }
      }
   }

   fun dispose() = job?.takeIf { !it.isCancelled }?.cancel()
}