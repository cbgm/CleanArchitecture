package com.distribution.christian.cleantest.event.data.repository.remote.event

import com.distribution.christian.cleantest.core.data.model.EventDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {

    @GET("events")
    fun getAllUsers(): Deferred<Response<List<EventDto>>>

    @GET("event")
    fun getEventById(@Query("eventId")eventId: String): Deferred<Response<EventDto>>
}