package com.example.christian.cleantest.cart.data.mapper

import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.core.data.model.UserDto
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserDtoMapperTests {

   private lateinit var userDtoMapper: UserDtoMapper

   private lateinit var userDto: UserDto
   private lateinit var user: User

   @Before
   fun setUp() {

      userDtoMapper = UserDtoMapper()

      userDto = UserDto(
            name = "Test",
            lastname = "Data"
      )

      user = User(
           name = "Test",
           lastname = "Data"
      )
   }

   @After
   fun cleanup() {

   }

   @Test
   @Throws
   fun testTransformDto() {
      val convertedUser : User = userDtoMapper.transform(userDto)
      Assert.assertEquals(user, convertedUser)
   }
}