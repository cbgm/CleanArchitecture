package com.distribution.christian.cleantest.core.domain.base

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.emit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout


abstract class BaseUseCase<T, in Params> {

    var job: Job? = null

    abstract suspend fun buildUseCaseObservable(param: Params): Result<Any>

    fun execute(observer: BaseObserver<T>? = null, param: Params) {
        dispose()
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            val result = buildUseCaseObservable(param)
            result.emit(observer)
        }
    }

    fun executeWithTimeout(observer: BaseObserver<T>? = null, param: Params, timeout: Long) {
        dispose()
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            val task = async(Dispatchers.IO) { buildUseCaseObservable(param) }
            //background thread
            try {
                val result = withTimeout(timeout) { task.await() }
                result.emit(observer)
            } catch (e: Exception) {
                observer?.onError(e)
            }
        }
    }

    fun executeLong(observer: BaseObserver<T>? = null, param: Params) {
        dispose()
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = buildUseCaseObservable(param)
                result.emit(observer)
            } catch (e: Exception) {
                observer?.onError(e)
            }
        }
    }

    fun dispose() = job?.takeIf { !it.isCancelled }?.cancel()
}