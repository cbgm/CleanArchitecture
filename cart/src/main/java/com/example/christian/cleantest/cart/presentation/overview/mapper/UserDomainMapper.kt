package com.example.christian.cleantest.cart.presentation.overview.mapper

import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.cart.presentation.overview.model.UserEntity
import com.example.christian.cleantest.cart.presentation.overview.model.UserOverviewEntity

class UserDomainMapper {

   fun transform(userOverview: UserOverview): UserOverviewEntity {
      return UserOverviewEntity(
            userOverview.count,
            userOverview
                  .users
                  .flatMap {
                     listOf(
                           transform(
                                 it
                           )
                     )
                  }.toCollection(
                        ArrayList()
                  )
      )
   }

   private fun transform(user: User): UserEntity {
      return UserEntity(
            user.name,
            user.lastname
      )
   }
}