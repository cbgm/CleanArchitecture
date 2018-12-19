package com.example.christian.cleantest.core.domain.completable

import com.example.christian.cleantest.core.domain.model.Result
import com.example.christian.cleantest.core.domain.model.onComplete
import com.example.christian.cleantest.core.domain.model.onError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class CompletableUseCase<in Params> {

   private var job: Job? = null

   abstract suspend fun buildUseCaseObservable(param: Params): Result<Any>

   fun execute(observer: DefaultCompletableObserver? = null, param: Params) {
      dispose()
      job = CoroutineScope(Dispatchers.Main).launch {
         val result = buildUseCaseObservable(param)
         result.onComplete { observer?.onComplete() }
         result.onError { observer?.onError(it) }
      }
   }

   private fun dispose() = job?.takeIf { !it.isCancelled }?.cancel()
}