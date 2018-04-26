package com.example.christian.cleantest.domain.usecases

import com.example.christian.cleantest.domain.model.UserOverview
import com.example.christian.cleantest.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository): SingleUseCase<UserOverview>() {

    override fun buildUseCaseObservable(params: SingleUseCase.Params?): Single<UserOverview> {
        return this.userRepository.getAllUsers()
    }

}