package com.distribution.christian.cleantest.profile.domain.repository

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview


interface ProfileRepository {

   suspend fun getProfile(): Result<ProfileOverview>

   suspend fun updateProfile(profileOverview: ProfileOverview): Result<ProfileOverview>
}