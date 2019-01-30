package com.distribution.christian.cleantest.profile.data.repository

import com.distribution.christian.cleantest.core.data.repository.remote.ProfileFromNetwork
import com.distribution.christian.cleantest.core.device.NetManager
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview
import com.distribution.christian.cleantest.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
      private val netManager: NetManager,
      private val profileFromNetwork: ProfileFromNetwork
): ProfileRepository {
   override suspend fun getProfile(): Result<ProfileOverview> {
      return profileFromNetwork.getProfile()
   }

   override suspend fun updatePRofile(profileOverview: ProfileOverview): Result<ProfileOverview> {
      return profileFromNetwork.updateProfile(profileOverview)
   }

}