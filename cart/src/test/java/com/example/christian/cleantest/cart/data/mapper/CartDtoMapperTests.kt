package com.example.christian.cleantest.cart.data.mapper

import com.example.christian.cleantest.cart.data.model.CartDto
import com.example.christian.cleantest.cart.domain.model.Cart
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest

@RunWith(JUnit4::class)
class CartDtoMapperTests : KoinTest {

   private lateinit var cartDtoMapper: CartDtoMapper

   private lateinit var cartDto: CartDto
   private lateinit var cart: Cart

   @Before
   fun setUp() {

      cartDtoMapper = CartDtoMapper()

      cartDto = CartDto(
            items = listOf("1", "2"),
            price = 12
      )

      cart = Cart(
            price = 12,
            items = listOf("1", "2")
      )
   }

   @After
   fun cleanup() {

   }

   @Test
   @Throws
   fun testTransformDto() {
      val convertedCart: Cart = cartDtoMapper.transform(cartDto)
      Assert.assertEquals(cart, convertedCart)
   }
}