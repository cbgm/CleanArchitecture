package com.example.christian.cleantest.core.domain.usecases

import com.example.christian.cleantest.core.data.service.PowerSaveModeServiceImpl
import com.example.christian.cleantest.core.domain.single.SingleUseCase
import io.reactivex.Single

class SwitchPowerSaveModeUseCase(
      private val powerSaveModeServiceImpl: PowerSaveModeServiceImpl
): SingleUseCase<Boolean, Unit>() {

   override fun buildUseCaseObservable(param: Unit): Single<Boolean> {
      return powerSaveModeServiceImpl.switchNightDay()
   }
}