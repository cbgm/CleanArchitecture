package com.example.christian.cleantest.cart.domain.usecases

import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.cart.domain.repository.UserRepository
import io.reactivex.Single

class GetUsersInPool constructor(private val userRepository: UserRepository): SingleUseCase<UserOverview, Unit>() {

    override fun buildUseCaseObservable(param: Unit): Single<UserOverview> {
        return this.userRepository.getAllUsers()
    }

}