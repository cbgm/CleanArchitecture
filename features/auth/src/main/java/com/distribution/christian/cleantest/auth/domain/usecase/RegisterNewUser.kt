package com.distribution.christian.cleantest.auth.domain.usecase

import com.distribution.christian.cleantest.core.domain.repository.AuthenticationRepository
import com.distribution.christian.cleantest.core.domain.completable.CompletableUseCase
import com.distribution.christian.cleantest.core.domain.model.Result

class RegisterNewUser(
      private val authenticationRepository: AuthenticationRepository
): CompletableUseCase<Pair<String, String>>() {
   override suspend fun buildUseCaseObservable(param: Pair<String, String>): Result<Any> {
      return authenticationRepository.registerUser(param.first, param.second)
   }
}