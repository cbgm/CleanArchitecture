package com.distribution.christian.cleantest.cart.presentation.overview.mapper

import com.distribution.christian.cleantest.cart.domain.model.User
import com.distribution.christian.cleantest.cart.domain.model.UserOverview
import com.distribution.christian.cleantest.cart.presentation.overview.model.UserEntity
import com.distribution.christian.cleantest.cart.presentation.overview.model.UserOverviewEntity

class UserDomainMapper {

    companion object {
        fun transform(userOverview: UserOverview) : UserOverviewEntity {
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

        private fun transform(user: User) : UserEntity {
            return UserEntity(
                  user.name,
                  user.lastname
            )
        }
    }
}