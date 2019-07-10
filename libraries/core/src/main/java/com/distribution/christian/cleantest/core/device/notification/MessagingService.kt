package com.distribution.christian.cleantest.core.device.notification

import com.distribution.christian.cleantest.core.domain.completable.DefaultCompletableObserver
import com.distribution.christian.cleantest.core.domain.usecase.ShowNotificationUseCase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.core.KoinComponent
import org.koin.core.inject


class MessagingService : FirebaseMessagingService(), KoinComponent {

   private val showNotificationUseCase: ShowNotificationUseCase by inject()

   override fun onMessageReceived(remoteMessage: RemoteMessage?) {
      remoteMessage?.let {
         showNotificationUseCase.execute(DefaultCompletableObserver(), it)
      }
   }
}