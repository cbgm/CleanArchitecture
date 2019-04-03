package com.distribution.christian.cleantest.event.data.repository.local

import com.distribution.christian.cleantest.event.data.repository.local.sql.SQLFactoryEvent


abstract class EventDaoFactory {

   abstract fun getEventDao(): EventDao

   companion object {
      const val SQL = 1
      const val ORACLE = 2
      const val ROOM = 3

      fun getDaoFactory(type: Int): EventDaoFactory {

         return when (type) {
            SQL -> SQLFactoryEvent()
            //ROOM -> EventCache()
            else -> SQLFactoryEvent()
         }
      }
   }
}