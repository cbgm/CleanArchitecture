package com.example.christian.cleantest.presentation.personalview

import android.graphics.Bitmap
import com.example.christian.cleantest.core.ui.BasePresenter
import com.example.christian.cleantest.core.ui.BaseView

interface PersonalContract {
    interface View: BaseView {
        fun initPersonalisation(bitmap: Bitmap?)
        fun updatePersonalisation(bitmap: Bitmap?)
    }

    interface Presenter: BasePresenter<View> {
        fun loadPersonalisation()
    }



}