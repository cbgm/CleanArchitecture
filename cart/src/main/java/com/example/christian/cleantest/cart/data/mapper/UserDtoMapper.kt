package com.example.christian.cleantest.cart.data.mapper

import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.core.data.model.UserDto

class UserDtoMapper {

    companion object {

        @Suppress("MemberVisibilityCanBePrivate")
        fun transform(user: UserDto) : User {
            return User(user.name, user.lastname)
        }
    }
}