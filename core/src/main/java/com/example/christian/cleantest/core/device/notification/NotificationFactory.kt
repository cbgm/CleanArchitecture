package com.example.christian.cleantest.core.device.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.christian.cleantest.core.R
import com.google.firebase.messaging.RemoteMessage

class NotificationFactory(val application: Application) {

   private val notificationManager: NotificationManager = application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
   val id = application.getString(R.string.default_notification_channel_id)
   val title = "default channel"

   enum class NotificationType {
      ALERT,
      HINT,
      DEFAULT
   }

   init {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         val importance = NotificationManager.IMPORTANCE_HIGH
         val channel: NotificationChannel
         channel = NotificationChannel(id, title, importance)
         channel.enableVibration(true)
         channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
         notificationManager.createNotificationChannel(channel)
      }
   }

   fun createNotification(
         remoteMessage: RemoteMessage,
         notificationType: NotificationType
   ): DefaultNotification {

      return when (notificationType) {
         NotificationType.ALERT -> AlertNotification(
               notificationManager,
               application,
               remoteMessage
         )
         NotificationType.HINT -> HintNotification(notificationManager, application, remoteMessage)
         else -> AlertNotification(notificationManager, application, remoteMessage)
      }
   }

}