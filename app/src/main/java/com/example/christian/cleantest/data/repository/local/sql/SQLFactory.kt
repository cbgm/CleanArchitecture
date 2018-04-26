package com.example.christian.cleantest.data.repository.local.sql

import com.example.christian.cleantest.data.repository.local.DaoFactory
import com.example.christian.cleantest.data.repository.local.UserDao

class SQLFactory: DaoFactory() {
    //create connection etc

    override fun getUserDao(): UserDao {
        return UserSQLDao()
    }
}