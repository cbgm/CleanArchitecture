package com.example.christian.cleantest.domain.usecases

import com.example.christian.cleantest.domain.model.UserOverview
import com.example.christian.cleantest.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUsersInPool @Inject constructor(private val userRepository: UserRepository): SingleUseCase<UserOverview, Unit>() {

    override fun buildUseCaseObservable(param: Unit): Single<UserOverview> {
        return this.userRepository.getAllUsers()
    }

}