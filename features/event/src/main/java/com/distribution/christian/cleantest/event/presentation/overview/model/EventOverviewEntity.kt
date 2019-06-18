package com.distribution.christian.cleantest.event.presentation.overview.model

import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity


data class EventOverviewEntity(val count: Int, val events: ArrayList<EventEntity>)