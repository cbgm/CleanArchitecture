package com.example.christian.cleantest.core.dagger.components

import com.example.christian.cleantest.core.dagger.annotations.ForActivity
import com.example.christian.cleantest.core.dagger.modules.ActivityModule
import com.example.christian.cleantest.presentation.cartview.CartActivity
import com.example.christian.cleantest.presentation.licenseview.LicenseActivity
import com.example.christian.cleantest.presentation.overview.OverviewActivity
import com.example.christian.cleantest.presentation.personalview.CropActivity
import com.example.christian.cleantest.presentation.personalview.PersonalActivity
import dagger.Subcomponent

@ForActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: OverviewActivity)
    fun inject(activity: CartActivity)
    fun inject(activity: PersonalActivity)
    fun inject(activity: CropActivity)
    fun inject(activity: LicenseActivity)
}