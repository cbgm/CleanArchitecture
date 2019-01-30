package com.distribution.christian.cleantest.profile.data.mapper

import com.distribution.christian.cleantest.core.data.model.SearchDto
import com.distribution.christian.cleantest.core.data.model.UserDto
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview

class ProfileOverviewDtoMapper {

   companion object {

      fun transform(userDto: UserDto, searchDto: SearchDto): ProfileOverview {
         return ProfileOverview(
               name = "${userDto.firstName} ${userDto.lastName}",
               email = userDto.email,
               password = userDto.password,
               birthDate = userDto.birthDate,
               alias = userDto.alias,
               city = searchDto.city,
               distance = searchDto.distance,
               type = searchDto.type,
               maxPrice = searchDto.maxPrice
         )
      }

      fun transform(profileOverview: ProfileOverview): Pair<UserDto, SearchDto> {
         return Pair(
               UserDto(
                     firstName = profileOverview.name.split(" ")[0],
                     lastName = profileOverview.name.split(" ")[1],
                     password = profileOverview.password,
                     birthDate = profileOverview.birthDate,
                     alias = profileOverview.alias,
                     email = profileOverview.email,
                     isUpgraded = false
                     ),
               SearchDto(
                     city = profileOverview.city,
                     distance = profileOverview.distance,
                     type = profileOverview.type,
                     maxPrice = profileOverview.maxPrice,
                     userId = profileOverview.alias
               )
         )
      }
   }
}