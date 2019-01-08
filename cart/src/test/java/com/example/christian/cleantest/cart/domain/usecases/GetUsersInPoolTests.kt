package com.example.christian.cleantest.cart.domain.usecases

import com.example.christian.cleantest.cart.core.di.cartMockModule
import com.example.christian.cleantest.cart.domain.mock.UserRepositoryMock
import com.example.christian.cleantest.cart.domain.repository.UserRepository
import com.example.christian.cleantest.core.domain.model.Result
import com.example.christian.cleantest.core.domain.model.onSuccess
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

@RunWith(JUnit4::class)
class GetUsersInPoolTests : KoinTest {

   private lateinit var getUsersInPool: GetUsersInPool
   private val userRepository: UserRepository by inject()

   @Before
   fun setup() {
      loadKoinModules(cartMockModule)
      getUsersInPool = GetUsersInPool(userRepository)
   }

   @After
   fun cleanup() {
      stopKoin()
   }

   @Test
   fun getUsers() {
      val result = runBlocking { getUsersInPool.buildUseCaseObservable(Unit) }
      if (result is Result.Success) {
         Assert.assertEquals(result.data, UserRepositoryMock.userOverview)
      }
   }
}