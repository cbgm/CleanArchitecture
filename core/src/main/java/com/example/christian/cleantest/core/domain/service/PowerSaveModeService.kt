package com.example.christian.cleantest.core.domain.service

import io.reactivex.Single

interface PowerSaveModeService {
   fun switchNightDay(): Single<Boolean>
}