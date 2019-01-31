package com.distribution.christian.cleantest.app

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.app.core.navigation.RootFlowCoordinatorImpl
import org.koin.android.ext.android.inject
import android.widget.TextView
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

   private val rootFlowCoordinatorImpl: RootFlowCoordinatorImpl by inject()

   private val title: TextView by lazy { findViewById<TextView>(R.id.textTitle) }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash2)
      startAnimation()
      Timber.v("release - debug switch test")
   }

   private fun startAnimation() {
      val startSize = 12f // Size in pixels
      val endSize = 38f
      val animationDuration: Long = 600
      val animator = ValueAnimator.ofFloat(startSize, endSize)
      animator.duration = animationDuration

      animator.addUpdateListener { valueAnimator ->
         val animatedValue = valueAnimator.animatedValue as Float
         title.textSize = animatedValue
      }
      animator.addListener(object : AnimatorListenerAdapter() {

         override fun onAnimationEnd(p0: Animator?) {
            startRouting()
         }
      })
      animator.start()
   }

   private fun startRouting() {
      val data: Uri? = intent?.data
       rootFlowCoordinatorImpl.start(this, data)
   }
}