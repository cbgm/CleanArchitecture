package com.example.christian.cleantest.core.koin

import com.example.christian.cleantest.core.device.NetManager
import org.koin.dsl.module.module

val networkModule = module {
   single { NetManager(get()) }

}