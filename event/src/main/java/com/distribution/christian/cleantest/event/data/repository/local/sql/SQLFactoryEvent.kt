package com.distribution.christian.cleantest.event.data.repository.local.sql

import com.distribution.christian.cleantest.event.data.repository.local.EventDaoFactory
import com.distribution.christian.cleantest.event.data.repository.local.EventDao


class SQLFactoryEvent: EventDaoFactory() {
    //create connection etc

    override fun getEventDao(): EventDao {
        return EventSQLDao()
    }
}