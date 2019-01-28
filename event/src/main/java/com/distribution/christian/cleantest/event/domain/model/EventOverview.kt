package com.distribution.christian.cleantest.event.domain.model

import com.distribution.christian.cleantest.core.domain.model.Event

data class EventOverview(val count: Int, val events: ArrayList<Event>)