package com.distribution.christian.cleantest.core.domain.completable

import com.distribution.christian.cleantest.core.domain.base.BaseUseCase


abstract class CompletableUseCase<in Params>: BaseUseCase<Unit, Params>()