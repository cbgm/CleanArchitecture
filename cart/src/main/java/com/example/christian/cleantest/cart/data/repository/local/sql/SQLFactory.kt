package com.example.christian.cleantest.cart.data.repository.local.sql

import com.example.christian.cleantest.cart.data.repository.local.DaoFactory
import com.example.christian.cleantest.cart.data.repository.local.UserDao
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SQLFactory : DaoFactory(), KoinComponent {

   private val userSQLDao: UserSQLDao by inject()
   //create connection etc

   override fun getUserDao(): UserDao {
      return userSQLDao
   }
}