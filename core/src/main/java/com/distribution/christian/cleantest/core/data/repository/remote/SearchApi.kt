package com.distribution.christian.cleantest.core.data.repository.remote

import com.distribution.christian.cleantest.core.data.model.SearchDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface SearchApi {

   @GET("search-configs/{id}")
   fun getSearchConfigByUserId(@Path("id") userId: String): Deferred<Response<SearchDto>>

   @PUT("search-configs/{id}")
   fun upateSearchConfigByUserId(@Path("id") userId: String, @Body searchDto: SearchDto): Deferred<Response<SearchDto>>
}