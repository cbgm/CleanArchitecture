package com.distribution.christian.cleantest.profile.domain.usecase

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview
import com.distribution.christian.cleantest.profile.domain.repository.ProfileRepository


class UpdateProfileOfAuthenticatedUser(
      private val profileRepository: ProfileRepository
): SingleUseCase<ProfileOverview, ProfileOverview>() {

   override suspend fun buildUseCaseObservable(param: ProfileOverview): Result<ProfileOverview> {
      return profileRepository.updateProfile(param)
   }
}