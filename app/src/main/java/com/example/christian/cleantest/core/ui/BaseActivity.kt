package com.example.christian.cleantest.core.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutInflater.inflate(getLayoutResId(), content)
        setSupportActionBar(toolbar)
    }


    abstract fun getLayoutResId(): Int

    override fun onBackPressed() {
        finish()
    }
}