package com.example.christian.cleantest.core.device.notification

import android.content.Context
import com.example.christian.cleantest.core.R
import com.google.firebase.messaging.RemoteMessage

abstract class DefaultNotification(
      private val context: Context,
      private val remoteMessage: RemoteMessage
) {

   var channelId = context.getString(R.string.default_notification_channel_id)
   val NOTIFY_ID = 0 // ID of notification

   abstract fun show()
}