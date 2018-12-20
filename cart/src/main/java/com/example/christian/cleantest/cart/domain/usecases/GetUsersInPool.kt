package com.example.christian.cleantest.cart.domain.usecases

import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.cart.domain.repository.UserRepository
import com.example.christian.cleantest.core.domain.single.SingleUseCase
import com.example.christian.cleantest.core.domain.model.Result

class GetUsersInPool constructor(private val userRepository: UserRepository) : SingleUseCase<UserOverview, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<UserOverview> {
      return userRepository.getAllUsers()
   }

}