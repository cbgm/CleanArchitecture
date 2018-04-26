package com.example.christian.cleantest.core.extensions

import android.app.Activity
import com.example.christian.cleantest.presentation.UserApplication

val Activity.app: UserApplication
    get() = application as UserApplication