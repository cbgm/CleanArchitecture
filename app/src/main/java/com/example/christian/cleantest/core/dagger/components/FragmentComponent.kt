package com.example.christian.cleantest.core.dagger.components

import com.example.christian.cleantest.core.dagger.annotations.ForFragment
import com.example.christian.cleantest.core.dagger.modules.FragmentModule
import com.example.christian.cleantest.presentation.cartview.CartFragment
import dagger.Subcomponent

@ForFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: CartFragment)
}