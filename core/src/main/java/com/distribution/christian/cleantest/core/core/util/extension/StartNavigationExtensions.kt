package com.distribution.christian.cleantest.core.core.util.extension

import com.distribution.christian.cleantest.core.BuildConfig.ACTION_SHOP
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_AUTH
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_PROFILE
import androidx.core.app.ActivityOptionsCompat
import android.view.View
import com.distribution.christian.cleantest.core.BuildConfig.ACTION_EVENTS
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
   startActivity(intentToStart)
}

fun Context.navigateToAuth(oldActivity: FragmentActivity, view: View? = null) {
   val intentToStart = Intent(ACTION_AUTH)

   if (view != null) {
      val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            oldActivity,
            view, // Starting view
            "test"    // The String
      )
      startAction(intentToStart, this, oldActivity, options.toBundle())
   } else {
      startAction(intentToStart, this, oldActivity)
   }

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