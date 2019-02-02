package com.distribution.christian.cleantest.core.device


class ToolbarManager (
        private var builder: FragmentToolbar
) {

    fun prepareToolbar() {

        if (builder.toolbar != null) {
            val fragmentToolbar = builder.toolbar

            fragmentToolbar?.let {

                if (builder.title != -1) {
                    fragmentToolbar.setTitle(builder.title)
                }

                if (builder.backButtonEnabled) {
                    fragmentToolbar.setDisplayShowHomeEnabled(false)
                    fragmentToolbar.setDisplayHomeAsUpEnabled(true)
                    //  fragmentToolbar.setHomeAsUpIndicator(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)

                    if (builder.backButtonAppearance != -1) {
                        fragmentToolbar.setHomeAsUpIndicator(builder.backButtonAppearance)
                    }
                }
            }
        }
    }
}