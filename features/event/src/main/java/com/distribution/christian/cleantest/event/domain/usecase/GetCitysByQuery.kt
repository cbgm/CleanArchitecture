package com.distribution.christian.cleantest.event.domain.usecase

import com.distribution.christian.cleantest.core.domain.single.SingleUseCase
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.repository.CityRepository


class GetCitysByQuery constructor(
      private val cityRepository: CityRepository
) : SingleUseCase<Array<String>, String>() {

   override suspend fun buildUseCaseObservable(param: String): Result<Array<String>> {
      return cityRepository.getCitysByQuery(param)
   }
}