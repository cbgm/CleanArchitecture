package com.distribution.christian.cleantest.profile.data.repository.remote

import com.distribution.christian.cleantest.core.core.util.extension.mapToResult
import com.distribution.christian.cleantest.core.data.repository.remote.SearchApi
import com.distribution.christian.cleantest.core.data.repository.remote.UserApi
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.profile.data.mapper.ProfileOverviewDtoMapper
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview


class ProfileFromNetwork constructor(
      private val userApi: UserApi,
      private val searchApi: SearchApi
) {

   suspend fun getProfile(): Result<ProfileOverview> {
      val response = userApi.authenticateUser()
            .await()
            .body()
            ?.let {
               val userDto = it
               val response2 = searchApi.getSearchConfigByUserId(it.alias)
                     .await()
               response2.mapToResult {
                  ProfileOverviewDtoMapper.transform(
                        userDto,
                        response2.body()!!
                  )
               }
            }
      return response!!
   }

   suspend fun updateProfile(profileOverview: ProfileOverview): Result<ProfileOverview> {
      val userSearch = ProfileOverviewDtoMapper.transform(profileOverview)


      val response = userApi.updateUser(userSearch.first)
            .await()
            .body()
            ?.let {
               val userDto = it
               val response2 = searchApi.upateSearchConfigByUserId(userDto.alias, userSearch.second)
                     .await()
               response2.mapToResult {
                  ProfileOverviewDtoMapper.transform(
                        userDto,
                        response2.body()!!
                  )
               }
            }
      return response!!
   }
}