package com.example.christian.cleantest.core.core.util.extension

import com.example.christian.cleantest.core.BuildConfig.ACTION_SHOP
import com.example.christian.cleantest.core.BuildConfig.ACTION_CART

import android.content.Context
import android.content.Intent

fun Context.navigateToshop() {
   val intentToStart = Intent(ACTION_SHOP)
   startAction(intentToStart, this)
}

fun Context.navigateToCart() {
   val intentToStart = Intent(ACTION_CART)
   startAction(intentToStart, this)
}

private fun startAction(intentToStart: Intent, context: Context) {
   intentToStart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
   context.startActivity(intentToStart)
}