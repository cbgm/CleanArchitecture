package com.distribution.christian.cleantest.app.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.FrankenCoordinatorManager
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkHandler
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.util.listener.AnimationPropertyEndListener
import org.koin.android.ext.android.inject
import timber.log.Timber


class SplashActivity : AppCompatActivity() {

   private val coordinatorManager: FrankenCoordinatorManager by inject()
   private val deepLinkHandler: DeepLinkHandler by inject()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash)
      animate()
      Timber.v("release - debug switch test")
   }

   private fun animate() {
      val logoImageView = findViewById<View>(R.id.img_logo) as ImageView
      val container = findViewById<View>(R.id.container) as ViewGroup

      ViewCompat.animate(logoImageView)
            .translationY(-250f)
            .setStartDelay(300)
            .setDuration(1000)
            .setInterpolator(
                  DecelerateInterpolator(1.2f)
            )
            .start()
      var viewAnimator: ViewPropertyAnimatorCompat

      for (i in 0 until container.childCount) {
         val v = container.getChildAt(i)


         viewAnimator = ViewCompat.animate(v)
               .translationY(10F)
               .alpha(1F)
               .setStartDelay(((300 * i) + 500).toLong())
               .setDuration(1000)

         viewAnimator.setInterpolator(DecelerateInterpolator())
               .start()

         if (i == container.childCount - 1) {
            viewAnimator.setListener(object : AnimationPropertyEndListener() {
               override fun onAnimationEnd(view: View?) {
                  startRouting()
               }
            })
         }
      }
   }

   private fun startRouting() {
      val data: Uri? = intent?.data
      data?.let {
         this.deepLinkHandler.registerDeepLink("shop", DeepLinkIdentifier.SHOP)
         this.deepLinkHandler.registerDeepLink("events", DeepLinkIdentifier.EVENTS)
         this.deepLinkHandler.registerDeepLink("detail", DeepLinkIdentifier.EVENT_DETAIL)
         this.deepLinkHandler.setUri(it)
      }
      coordinatorManager.applicationPartCoordinator.start(this)
   }
}