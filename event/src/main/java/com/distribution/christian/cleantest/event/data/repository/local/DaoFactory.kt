package com.distribution.christian.cleantest.event.data.repository.local

import com.distribution.christian.cleantest.event.data.repository.local.sql.SQLFactory


abstract class DaoFactory {

   abstract fun getEventDao(): EventDao

   companion object {
      const val SQL = 1
      const val ORACLE = 2

      fun getDaoFactory(type: Int): DaoFactory {

         return when (type) {
            SQL -> SQLFactory()
            else -> SQLFactory()
         }
      }
   }
}