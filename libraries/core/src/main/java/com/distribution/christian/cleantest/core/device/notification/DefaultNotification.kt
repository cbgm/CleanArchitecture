package com.distribution.christian.cleantest.core.device.notification

import android.content.Context
import com.google.firebase.messaging.RemoteMessage


abstract class DefaultNotification(
      protected val context: Context,
      protected val remoteMessage: RemoteMessage
) {
   abstract fun show()
}