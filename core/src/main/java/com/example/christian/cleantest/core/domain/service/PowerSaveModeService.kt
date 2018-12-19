package com.example.christian.cleantest.core.domain.service

import com.example.christian.cleantest.core.domain.model.Result

interface PowerSaveModeService {
   fun switchNightDay(): Result<Boolean>
}