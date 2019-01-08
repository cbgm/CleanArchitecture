package com.example.christian.cleantest.cart.domain.mock

import com.example.christian.cleantest.cart.domain.model.Cart
import com.example.christian.cleantest.cart.domain.model.User
import com.example.christian.cleantest.cart.domain.repository.CartRepository
import com.example.christian.cleantest.core.domain.model.Result

class CartRepositoryMock constructor(
      private val userCart: HashMap<String, Cart>
) : CartRepository {

   companion object {

      private val users = arrayListOf(
            User("name1", "lastname1"),
            User("name2", "lastname2"),
            User("name3", "lastname3")
      )

      val userCart = HashMap<String, Cart>().apply {
         for (user in users)
            this[user.name] = Cart(
                  50 + user.name.length,
                  listOf(
                        "item1",
                        "item2"
                  )
            )
      }

      fun instance(): CartRepository {
         return CartRepositoryMock(userCart)
      }
   }

   override suspend fun getCart(userId: String): Result<Cart> {
      val cart = userCart[userId]
      return if (cart != null) {
         Result.Success(cart)
      } else {
         Result.Error(NullPointerException())
      }
   }
}