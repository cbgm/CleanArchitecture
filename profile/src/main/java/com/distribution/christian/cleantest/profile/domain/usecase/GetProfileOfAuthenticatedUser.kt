package com.distribution.christian.cleantest.profile.domain.usecase

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview
import com.distribution.christian.cleantest.profile.domain.repository.ProfileRepository


class GetProfileOfAuthenticatedUser(
      private val profileRepository: ProfileRepository
): SingleUseCase<ProfileOverview, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<ProfileOverview> {
      return profileRepository.getProfile()
   }
}