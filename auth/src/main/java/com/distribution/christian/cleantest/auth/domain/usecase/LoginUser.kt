package com.distribution.christian.cleantest.auth.domain.usecase

import com.distribution.christian.cleantest.auth.domain.repository.AuthenticationRepository
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.User
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase

class LoginUser(
      private val authenticationRepository: AuthenticationRepository
) : SingleUseCase<User, Pair<String, String>>() {
   override suspend fun buildUseCaseObservable(param: Pair<String, String>): Result<Any> {
      return authenticationRepository.loginUser(param.first, param.second)
   }
}