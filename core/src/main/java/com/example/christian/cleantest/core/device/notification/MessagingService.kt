package com.example.christian.cleantest.core.device.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MessagingService : FirebaseMessagingService() {

   private val notificationFactory by lazy {
      NotificationFactory(application)
   }

   override fun onMessageReceived(remoteMessage: RemoteMessage?) {
      remoteMessage?.let {
         notificationFactory.createNotification(it, NotificationFactory.NotificationType.HINT).show()
      }
   }
}