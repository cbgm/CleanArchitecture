package com.distribution.christian.cleantest.core.domain.service

import com.distribution.christian.cleantest.core.domain.model.Result


interface PowerSaveModeService {

   fun switchNightDay(): Result<Boolean>
}