package com.example.christian.cleantest.core.dagger.components

import com.example.christian.cleantest.core.dagger.annotations.ForActivity
import com.example.christian.cleantest.core.dagger.modules.ActivityModule
import com.example.christian.cleantest.presentation.overview.OverviewActivity
import dagger.Subcomponent

@ForActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: OverviewActivity)
}