package com.example.christian.cleantest.core.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.dagger.modules.ActivityModule
import com.example.christian.cleantest.core.extensions.app
import com.example.christian.cleantest.device.FragmentToolbar
import com.example.christian.cleantest.device.ToolbarLoader
import com.example.christian.cleantest.device.ToolbarManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity: AppCompatActivity() {

    //protected val component by lazy { app.component.plus(ActivityModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutInflater.inflate(getLayoutResId(), content)
        setSupportActionBar(toolbar)
        //ToolbarManager(toolbarBuilder()).prepareToolbar()
    }


    abstract fun getLayoutResId(): Int

    abstract fun toolbarBuilder() : FragmentToolbar

}