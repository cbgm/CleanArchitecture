package com.distribution.christian.cleantest.profile.domain.usecase

import com.distribution.christian.cleantest.core.domain.repository.AuthenticationRepository
import com.distribution.christian.cleantest.core.domain.completable.CompletableUseCase
import com.distribution.christian.cleantest.core.domain.model.Result

class LogoutUser(
      private val authenticationRepository: AuthenticationRepository
) : CompletableUseCase<Unit>() {
   override suspend fun buildUseCaseObservable(param: Unit): Result<Any> {
      return authenticationRepository.logoutUser()
   }
}