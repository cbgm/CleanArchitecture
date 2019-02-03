package com.distribution.christian.cleantest.auth.domain.usecase

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase

class IsUserAuthenticated: SingleUseCase<User, Unit>() {
   override suspend fun buildUseCaseObservable(param: Unit): Result<User> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
   }
}