package com.example.christian.cleantest.core.domain.base

import com.example.christian.cleantest.core.domain.model.Result
import com.example.christian.cleantest.core.domain.model.onError
import com.example.christian.cleantest.core.domain.model.onSuccess
import com.example.christian.cleantest.core.domain.model.onComplete
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
abstract class BaseUseCase<T, in Params> {

   var job: Job? = null

   abstract suspend fun buildUseCaseObservable(param: Params): Result<Any>

   fun execute(observer: BaseObserver<T>? = null, param: Params) {
      dispose()
      job = CoroutineScope(Dispatchers.Main).launch {
         val result = buildUseCaseObservable(param)
         result.onSuccess { observer?.onSuccess(it as T) }
         result.onComplete{ observer?.onComplete()}
         result.onError { observer?.onError(it) }
      }
   }

   fun dispose() = job?.takeIf { !it.isCancelled }?.cancel()
}