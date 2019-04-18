package com.distribution.christian.cleantest.core.domain.usecase

import com.distribution.christian.cleantest.core.data.service.NetworkChangeModeServiceImpl
import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.single.SingleUseCase


class SwitchNetworkModeUseCase(
      private val networkChangeModeServiceImpl: NetworkChangeModeServiceImpl
) : SingleUseCase<Boolean, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<Boolean> {
      return networkChangeModeServiceImpl.isNetworkAvailable()
   }
}