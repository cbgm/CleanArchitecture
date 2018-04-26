package com.example.christian.cleantest.data.repository.local

import com.example.christian.cleantest.data.repository.local.sql.SQLFactory

abstract class DaoFactory {

    abstract fun getUserDao(): UserDao

    companion object {
        const val SQL = 1
        const val ORACLE = 2

        fun getDaoFactory(type: Int): DaoFactory {

            return when(type){
                SQL -> SQLFactory()
                else -> SQLFactory()

            }
        }
    }
}