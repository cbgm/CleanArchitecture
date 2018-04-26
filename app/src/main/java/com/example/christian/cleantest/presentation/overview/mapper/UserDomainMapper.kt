package com.example.christian.cleantest.presentation.overview.mapper

import com.example.christian.cleantest.domain.model.User
import com.example.christian.cleantest.domain.model.UserOverview
import com.example.christian.cleantest.presentation.overview.model.UserEntity
import com.example.christian.cleantest.presentation.overview.model.UserOverviewEntity

class UserDomainMapper {

    companion object {
        fun transform(userOverview: UserOverview) : UserOverviewEntity{
            return UserOverviewEntity(userOverview.count, userOverview
                    .users
                    .flatMap{ listOf(transform(it)) }.toCollection(ArrayList()))
        }

        private fun transform(user: User) : UserEntity {
            return UserEntity(user.name, user.lastname)
        }
    }
}