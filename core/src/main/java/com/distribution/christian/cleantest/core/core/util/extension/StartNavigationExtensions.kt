package com.distribution.christian.cleantest.core.core.util.extension

import com.distribution.christian.cleantest.core.BuildConfig.ACTION_SHOP
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_CART

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_AUTH
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_PROFILE
import android.support.v4.app.ActivityCompat
import android.R.attr.transitionName
import android.support.v4.app.ActivityOptionsCompat
import android.view.View


fun Context.navigateToShop(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_SHOP)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateToProfile(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_PROFILE)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateToEvents(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_CART)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateToAuth(oldActivity: FragmentActivity, view: View) {
   val intentToStart = Intent(ACTION_AUTH)
   val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
         oldActivity,
         view, // Starting view
         "test"    // The String
   )
   //start the intent
   this.startActivity( intentToStart, options.toBundle())
   //oldActivity.finish()
   //startAction(intentToStart, this, oldActivity)
}

private fun startAction(intentToStart: Intent, context: Context, oldActivity: FragmentActivity? = null) {
   intentToStart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
   context.startActivity(intentToStart)
   oldActivity?.finish()
}