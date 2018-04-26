package com.example.christian.cleantest.data.mapper

import com.example.christian.cleantest.domain.model.User
import com.example.christian.cleantest.data.model.UserDto

class UserDtoMapper {

    companion object {
        /*fun transform(userOverview: UserOverviewDto) : UserOverview{
            return UserOverview(userOverview.count, userOverview
                    .users
                    .flatMap{ listOf(transform(it)) }.toCollection(ArrayList()))
        }*/

        @Suppress("MemberVisibilityCanBePrivate")
        fun transform(user: UserDto) : User {
            return User(user.name, user.lastname)
        }

        fun transform(users: ArrayList<UserDto>) : ArrayList<User> {
            return ArrayList(users.map { transform(it) })
        }
    }
}