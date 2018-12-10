package com.example.christian.cleantest.core.device.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.example.christian.cleantest.core.BuildConfig
import com.example.christian.cleantest.core.R
import com.google.firebase.messaging.RemoteMessage

class AlertNotification(
      private val notificationManager: NotificationManager,
      context: Context,
      remoteMessage: RemoteMessage

) : DefaultNotification(context, remoteMessage) {

   private var notification: Notification? = null

   init {
      val intent = Intent(BuildConfig.ACTION_CART)
      val pendingIntent: PendingIntent
      val builder = NotificationCompat.Builder(context, channelId)

      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
      pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
      builder.setContentTitle(remoteMessage.notification?.title)                            // required
            .setSmallIcon(android.R.drawable.ic_delete)   // required
            .setContentText(context.getString(R.string.app_name)) // required
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setTicker(remoteMessage.notification?.body)
            .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
            .priority = NotificationCompat.PRIORITY_HIGH
      notification = builder.build()
   }

   override fun show() {
      notificationManager.notify(NOTIFY_ID, notification)
   }
}