package com.example.christian.cleantest.core.core.util.extension

import com.example.christian.cleantest.core.BuildConfig.ACTION_SHOP
import com.example.christian.cleantest.core.BuildConfig.ACTION_CART

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity

fun Context.navigateToshop(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_SHOP)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateToCart(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_CART)
   startAction(intentToStart, this, oldActivity)
}

private fun startAction(intentToStart: Intent, context: Context, oldActivity: FragmentActivity? = null) {
   intentToStart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
   context.startActivity(intentToStart)
   oldActivity?.finish()
}