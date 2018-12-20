package com.example.christian.cleantest.core.domain.completable

import com.example.christian.cleantest.core.domain.base.BaseUseCase
import com.example.christian.cleantest.core.domain.model.Result
import com.example.christian.cleantest.core.domain.model.onComplete
import com.example.christian.cleantest.core.domain.model.onError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class CompletableUseCase<in Params>: BaseUseCase<Unit, Params>()