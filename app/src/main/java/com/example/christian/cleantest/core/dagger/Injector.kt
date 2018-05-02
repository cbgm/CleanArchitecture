package com.example.christian.cleantest.core.dagger

import android.app.Activity
import android.support.v4.app.Fragment
import com.example.christian.cleantest.core.dagger.components.ActivityComponent
import com.example.christian.cleantest.core.dagger.components.AppComponent
import com.example.christian.cleantest.core.dagger.components.DaggerAppComponent
import com.example.christian.cleantest.core.dagger.components.FragmentComponent
import com.example.christian.cleantest.core.dagger.modules.ActivityModule
import com.example.christian.cleantest.core.dagger.modules.AppModule
import com.example.christian.cleantest.core.dagger.modules.FragmentModule
import com.example.christian.cleantest.presentation.UserApplication

class Injector {

    companion object {

        private lateinit var appComponent: AppComponent
        private var activityComponent: ActivityComponent? = null
        private var fragmentComponent: FragmentComponent? = null

        fun init(application: UserApplication): AppComponent {
            appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
            return appComponent
        }

        fun initActivityComponent(activity: Activity): ActivityComponent {
            val temp = appComponent.plus(ActivityModule(activity))
            activityComponent = temp
            return temp

        }

        fun initFragmentComponent(fragment: Fragment): FragmentComponent {
            val temp = appComponent.plus(FragmentModule(fragment))
            fragmentComponent = temp
            return temp

        }
    }
}