package com.distribution.christian.cleantest.auth.domain.usecase

import com.distribution.christian.cleantest.core.domain.completable.CompletableUseCase
import com.distribution.christian.cleantest.core.domain.model.Result

class ResetUser: CompletableUseCase<Unit>() {
   override suspend fun buildUseCaseObservable(param: Unit): Result<Any> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }
}