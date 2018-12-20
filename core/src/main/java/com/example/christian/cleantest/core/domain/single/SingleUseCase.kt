package com.example.christian.cleantest.core.domain.single

import com.example.christian.cleantest.core.domain.base.BaseUseCase
import com.example.christian.cleantest.core.domain.model.Result
import com.example.christian.cleantest.core.domain.model.onError
import com.example.christian.cleantest.core.domain.model.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
abstract class SingleUseCase<T, in Params>: BaseUseCase<T, Params>()