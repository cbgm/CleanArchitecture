package com.distribution.christian.cleantest.core.domain.usecase

import com.distribution.christian.cleantest.core.device.notification.NotificationFactory
import com.distribution.christian.cleantest.core.domain.completable.CompletableUseCase
import com.distribution.christian.cleantest.core.domain.model.Result
import com.google.firebase.messaging.RemoteMessage
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ShowNotificationUseCase : CompletableUseCase<RemoteMessage>(), KoinComponent {

   private val notificationFactory: NotificationFactory by inject()

   override suspend fun buildUseCaseObservable(param: RemoteMessage): Result<RemoteMessage> {
      notificationFactory.createNotification(
            param,
            NotificationFactory.NotificationType.HINT
      )
            .show()
      return  Result.Complete()
   }
}