package com.distribution.christian.cleantest.event.data.repository.remote.event

import com.distribution.christian.cleantest.event.data.model.EventDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface EventApi {

   @GET("events")
   fun getAllUsers(): Deferred<Response<List<EventDto>>>

   @GET("events/{id}")
   fun getEventById(@Path("id") eventId: String): Deferred<Response<EventDto>>

   @PUT("events/{id}")
   fun updateEvent(@Path("id") eventId: String, @Body eventDto: EventDto): Deferred<Response<EventDto>>
}