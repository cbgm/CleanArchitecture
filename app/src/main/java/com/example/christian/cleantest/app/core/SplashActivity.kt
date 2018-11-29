package com.example.christian.cleantest.app.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.christian.cleantest.core.core.util.extension.navigateToCart

class SplashActivity: AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      navigateToCart()

   }


}