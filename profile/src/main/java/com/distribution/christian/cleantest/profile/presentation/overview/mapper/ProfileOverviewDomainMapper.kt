package com.distribution.christian.cleantest.profile.presentation.overview.mapper


import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview
import com.distribution.christian.cleantest.profile.presentation.overview.model.ProfileOverviewEntity

class ProfileOverviewDomainMapper {

   companion object {
      fun transform(profileOverview: ProfileOverview): ProfileOverviewEntity {
         return ProfileOverviewEntity(
               name = profileOverview.name,
               email = profileOverview.email,
               password = profileOverview.password,
               birthDate = profileOverview.birthDate,
               alias = profileOverview.alias,
               city = profileOverview.city,
               distance = profileOverview.distance,
               type = profileOverview.type,
               maxPrice = profileOverview.maxPrice
         )
      }

      fun transform(profileOverviewEntity: ProfileOverviewEntity): ProfileOverview {
         return ProfileOverview(
               name = profileOverviewEntity.name,
               email = profileOverviewEntity.email,
               password = profileOverviewEntity.password,
               birthDate = profileOverviewEntity.birthDate,
               alias = profileOverviewEntity.alias,
               city = profileOverviewEntity.city,
               distance = profileOverviewEntity.distance,
               type = profileOverviewEntity.type,
               maxPrice = profileOverviewEntity.maxPrice
         )
      }
   }
}