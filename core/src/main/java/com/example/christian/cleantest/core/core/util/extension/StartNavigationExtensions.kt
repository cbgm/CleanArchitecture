package com.example.christian.cleantest.core.core.util.extension

import com.example.christian.cleantest.core.BuildConfig.ACTION_SHOP
import com.example.christian.cleantest.core.BuildConfig.ACTION_CART

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity

fun Context.navigateToshop(oldAc: FragmentActivity) {
   val intentToStart = Intent(ACTION_SHOP)
   startAction(intentToStart, this, oldAc)
}

fun Context.navigateToCart(oldAc: FragmentActivity) {
   val intentToStart = Intent(ACTION_CART)
   startAction(intentToStart, this, oldAc)
}

private fun startAction(intentToStart: Intent, context: Context, oldAc: FragmentActivity? = null) {
   intentToStart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
   context.startActivity(intentToStart)
   oldAc?.finish()
}