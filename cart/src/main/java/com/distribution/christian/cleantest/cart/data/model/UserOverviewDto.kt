package com.distribution.christian.cleantest.cart.data.model

import com.distribution.christian.cleantest.core.data.model.UserDto

data class UserOverviewDto(val count: Int, val users: ArrayList<UserDto>)