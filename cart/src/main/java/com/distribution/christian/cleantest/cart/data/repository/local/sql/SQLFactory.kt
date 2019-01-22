package com.distribution.christian.cleantest.cart.data.repository.local.sql

import com.distribution.christian.cleantest.cart.data.repository.local.DaoFactory
import com.distribution.christian.cleantest.cart.data.repository.local.UserDao

class SQLFactory: DaoFactory() {
    //create connection etc

    override fun getUserDao(): UserDao {
        return UserSQLDao()
    }
}