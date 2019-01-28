package com.distribution.christian.cleantest.event.data.model

import com.distribution.christian.cleantest.core.data.model.EventDto

data class EventOverviewDto(val count: Int, val events: ArrayList<EventDto>)