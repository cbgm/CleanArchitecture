package com.example.christian.cleantest.cart.domain.usecases

import com.example.christian.cleantest.cart.core.di.cartMockModule
import com.example.christian.cleantest.cart.domain.mock.CartRepositoryMock
import com.example.christian.cleantest.cart.domain.repository.CartRepository
import com.example.christian.cleantest.core.domain.model.Result
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import java.lang.NullPointerException

@RunWith(JUnit4::class)
class GetCartByUserTests : KoinTest {

   private lateinit var getCartByUser: GetCartByUser
   private val cartRepository: CartRepository by inject()

   @Before
   fun setup() {
      loadKoinModules(cartMockModule)
      getCartByUser = GetCartByUser(cartRepository)
   }

   @After
   fun cleanup() {
      stopKoin()
   }

   @Test
   fun getCartwithIdAvailable() {
      val result = runBlocking { getCartByUser.buildUseCaseObservable("name1") }
      if (result is Result.Success) {
         Assert.assertEquals(result.data, CartRepositoryMock.userCart["name1"])
      }
   }

   @Test(expected = NullPointerException::class)
   fun getCartwithIdUnavailable() {
      val result = runBlocking { getCartByUser.buildUseCaseObservable("name5") }
      if (result is Result.Error) {
         throw result.exception!!
      }
   }
}