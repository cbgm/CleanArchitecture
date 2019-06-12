package com.distribution.christian.cleantest.core.core.util.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_AUTH
import androidx.core.app.ActivityOptionsCompat
import android.view.View
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_EVENTS
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_PROFILE
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_SHOP
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_STARS


fun Context.navigateToShop(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_SHOP)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateToProfile(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_PROFILE)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateToEvents(oldActivity: FragmentActivity) {
   val intentToStart = Intent(ACTION_EVENTS)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateToStars() {
   val intentToStart = Intent(ACTION_STARS)
   val bundle = ActivityOptionsCompat.makeCustomAnimation(
         this,
         android.R.anim.fade_in, android.R.anim.fade_out
   )
         .toBundle()
   startActivity(intentToStart, bundle)
}

fun Context.navigateToAuth(oldActivity: FragmentActivity, view: View? = null) {
   val intentToStart = Intent(ACTION_AUTH)
   startAction(intentToStart, this, oldActivity)
}

fun Context.navigateGoogleMaps(location: String) {
   val gmmIntentUri = Uri.parse("google.navigation:q=" + location);
   val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri);
   mapIntent.setPackage("com.google.android.apps.maps")
   startActivity(mapIntent)
}

private fun startAction(
      intentToStart: Intent,
      context: Context,
      oldActivity: FragmentActivity? = null,
      bundle: Bundle? = null
) {
   intentToStart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
   context.startActivity(intentToStart, bundle)
   oldActivity?.finish()
}