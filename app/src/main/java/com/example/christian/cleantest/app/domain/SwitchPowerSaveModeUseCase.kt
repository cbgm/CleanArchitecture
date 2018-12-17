package com.example.christian.cleantest.app.domain

import com.example.christian.cleantest.app.core.power.PowerSaveModeService
import com.example.christian.cleantest.core.domain.single.SingleUseCase
import io.reactivex.Single

class SwitchPowerSaveModeUseCase(
      private val powerSaveModeService: PowerSaveModeService
): SingleUseCase<Boolean, Unit>() {

   override fun buildUseCaseObservable(param: Unit): Single<Boolean> {
      return powerSaveModeService.switchNightDay()
   }
}