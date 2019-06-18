package com.distribution.christian.cleantest.auth.domain.usecase

import com.distribution.christian.cleantest.core.domain.completable.CompletableUseCase
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.repository.AuthenticationRepository
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase

class GetAuthenticatedUser(
      private val authenticationRepository: AuthenticationRepository
): SingleUseCase<User, Unit>() {
   override suspend fun buildUseCaseObservable(param: Unit): Result<User> {
      return authenticationRepository.getAuthenticatedUser()
   }
}