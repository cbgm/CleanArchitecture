package com.distribution.christian.cleantest.event.data.repository.local.sql

import com.distribution.christian.cleantest.event.data.repository.local.DaoFactory
import com.distribution.christian.cleantest.event.data.repository.local.EventDao

class SQLFactory: DaoFactory() {
    //create connection etc

    override fun getEventDao(): EventDao {
        return EventSQLDao()
    }
}