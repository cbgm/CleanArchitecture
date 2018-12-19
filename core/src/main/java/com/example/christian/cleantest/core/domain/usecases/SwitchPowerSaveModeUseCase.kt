package com.example.christian.cleantest.core.domain.usecases

import com.example.christian.cleantest.core.data.service.PowerSaveModeServiceImpl
import com.example.christian.cleantest.core.domain.default.DefaultUseCase
import com.example.christian.cleantest.core.domain.model.Result

class SwitchPowerSaveModeUseCase(
      private val powerSaveModeServiceImpl: PowerSaveModeServiceImpl
) : DefaultUseCase<Boolean, Unit>() {

   override suspend fun buildUseCaseObservable(param: Unit): Result<Boolean> {
      return powerSaveModeServiceImpl.switchNightDay()
   }
}