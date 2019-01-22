package com.distribution.christian.cleantest.cart.domain.usecases

import com.distribution.christian.cleantest.cart.domain.model.UserOverview
import com.distribution.christian.cleantest.cart.domain.repository.UserRepository
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.core.domain.model.Result

class GetUsersInPool constructor(private val userRepository: UserRepository) : SingleUseCase<UserOverview, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<UserOverview> {
      return userRepository.getAllUsers()
   }

}