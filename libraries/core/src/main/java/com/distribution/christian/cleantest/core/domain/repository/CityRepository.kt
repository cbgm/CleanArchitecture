package com.distribution.christian.cleantest.core.domain.repository

import com.distribution.christian.cleantest.core.domain.model.Result


interface CityRepository {

   suspend fun getCitysByQuery(query: String): Result<Array<String>>
}