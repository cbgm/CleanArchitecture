package com.distribution.christian.cleantest.core.device.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.google.firebase.messaging.RemoteMessage

class NotificationFactory(val application: Application) {

   private val notificationManager: NotificationManager = application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

   companion object {
      private const val CHANNEL_ID = "fcm_default_channel"
      private const val CHANNEL_TITLE = "default channel"
   }

   enum class NotificationType {
      ALERT,
      HINT,
      DEFAULT
   }

   init {

   }

   fun createNotification(
         remoteMessage: RemoteMessage,
         notificationType: NotificationType
   ): DefaultNotification {

      return when (notificationType) {
         NotificationType.ALERT -> AlertNotification(
               notificationManager.setChannel(),
               CHANNEL_ID,
               application,
               remoteMessage
         )
         NotificationType.HINT -> HintNotification(
               notificationManager.setChannel(),
               CHANNEL_ID,
               application,
               remoteMessage
         )
         else -> AlertNotification(notificationManager, CHANNEL_ID, application, remoteMessage)
      }
   }

   private fun NotificationManager.setChannel(): NotificationManager {

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         val importance = NotificationManager.IMPORTANCE_HIGH
         val channel: NotificationChannel
         channel = NotificationChannel(CHANNEL_ID, CHANNEL_TITLE, importance)
         channel.enableVibration(true)
         channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
         this.createNotificationChannel(channel)
      }
      return this
   }
}