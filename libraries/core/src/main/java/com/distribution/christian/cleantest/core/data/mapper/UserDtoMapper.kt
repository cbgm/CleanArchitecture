package com.distribution.christian.cleantest.core.data.mapper

import com.distribution.christian.cleantest.core.data.model.UserDto
import com.distribution.christian.cleantest.core.domain.model.User


class UserDtoMapper {

   companion object {
      fun transform(userDto: UserDto) : User{
         return User(
               firstName = userDto.firstName,
               lastName = userDto.lastName,
               password = userDto.password,
               email = userDto.email,
               isUpgraded = userDto.isUpgraded,
               alias = userDto.alias,
               birthDate = userDto.birthDate
         )
      }

      fun transform(user: User) : UserDto{
         return UserDto(
               firstName = user.firstName,
               lastName = user.lastName,
               password = user.password,
               email = user.email,
               isUpgraded = user.isUpgraded,
               alias = user.alias,
               birthDate = user.birthDate
         )
      }
   }
}