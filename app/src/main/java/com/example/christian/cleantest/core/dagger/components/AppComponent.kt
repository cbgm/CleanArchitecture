package com.example.christian.cleantest.core.dagger.components

import com.example.christian.cleantest.core.dagger.modules.AppModule
import com.example.christian.cleantest.core.dagger.modules.ActivityModule
import com.example.christian.cleantest.core.dagger.modules.FragmentModule
import com.example.christian.cleantest.core.dagger.modules.RepositoryModule
import com.example.christian.cleantest.device.ToolbarLoader
import com.example.christian.cleantest.presentation.UserApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(app: UserApplication)
    fun plus(activityModule: ActivityModule): ActivityComponent
    fun plus(fragmentModule: FragmentModule): FragmentComponent
}