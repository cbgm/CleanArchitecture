package com.example.christian.cleantest.core.core.di

import com.example.christian.cleantest.core.device.NetManager
import org.koin.dsl.module.module

val networkModule = module {
   single { NetManager(get()) }

}