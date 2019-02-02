package com.distribution.christian.cleantest.profile.data.model

import com.distribution.christian.cleantest.core.data.model.SearchDto
import com.distribution.christian.cleantest.core.data.model.UserDto


data class ProfileOverviewDto(val userDto: UserDto, val searchDto: SearchDto)