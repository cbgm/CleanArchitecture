package com.distribution.christian.cleantest.core.data.repository

import com.distribution.christian.cleantest.core.data.repository.local.CityFromLocal
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.repository.CityRepository


class CityRepositoryImpl constructor(
      private val cityFromLocal: CityFromLocal
) : CityRepository {

   override suspend fun getCitysByQuery(query: String): Result<Array<String>> {
      return cityFromLocal.getFilteredCitys(query)
   }
}