package com.example.christian.cleantest.core.domain.usecases

import com.example.christian.cleantest.core.data.service.PowerSaveModeServiceImpl
import com.example.christian.cleantest.core.domain.model.Result
import com.example.christian.cleantest.core.domain.single.SingleUseCase

class SwitchPowerSaveModeUseCase(
      private val powerSaveModeServiceImpl: PowerSaveModeServiceImpl
) : SingleUseCase<Boolean, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<Boolean> {
      return powerSaveModeServiceImpl.switchNightDay()
   }
}