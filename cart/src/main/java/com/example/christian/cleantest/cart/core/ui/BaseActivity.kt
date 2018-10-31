package com.example.christian.cleantest.cart.core.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.cart.R
import kotlinx.android.synthetic.main.activity_main.content
import kotlinx.android.synthetic.main.toolbar.toolbar

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