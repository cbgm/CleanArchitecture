package com.example.christian.cleantest.device

import android.support.annotation.DrawableRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v7.app.ActionBar
import android.view.MenuItem

class FragmentToolbar(
        val toolbar: ActionBar?,
        @StringRes val title: Int,
        val backButtonEnabled: Boolean,
        @DrawableRes val backButtonAppearance: Int
) {

    class Builder {
        private var toolbar: ActionBar? = null
        private var menuId: Int = -1
        private var title: Int = -1
        private var backButtonEnabled: Boolean = false
        private var backButtonAppearance: Int = -1
        private var menuItems: MutableList<Int> = mutableListOf()
        private var menuClicks: MutableList<MenuItem.OnMenuItemClickListener?> = mutableListOf()

        fun withBarReference(toolbar: ActionBar) = apply { this.toolbar = toolbar}

        fun withTitle(title: Int) = apply { this.title = title }

        fun withBackButton(backButtonEnabled: Boolean) = apply { this.backButtonEnabled = backButtonEnabled }

        fun withBackButttonAppearance(backButtonAppearance: Int) = apply {this.backButtonAppearance = backButtonAppearance}

        fun withMenu(@MenuRes menuId: Int) = apply { this.menuId = menuId }

        fun withMenuItems(menuItems: MutableList<Int>, menuClicks: MutableList<MenuItem.OnMenuItemClickListener?>) = apply {
            this.menuItems.addAll(menuItems)
            this.menuClicks.addAll(menuClicks)
        }

        fun build() = FragmentToolbar(toolbar, title, backButtonEnabled, backButtonAppearance)
    }
}