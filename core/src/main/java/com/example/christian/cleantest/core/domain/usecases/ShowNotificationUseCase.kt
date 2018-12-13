package com.example.christian.cleantest.core.domain.usecases

import com.example.christian.cleantest.core.device.notification.NotificationFactory
import com.example.christian.cleantest.core.domain.completable.CompletableUseCase
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.Completable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ShowNotificationUseCase : CompletableUseCase<RemoteMessage>(), KoinComponent {

   private val notificationFactory: NotificationFactory by inject()

   override fun buildUseCaseObservable(param: RemoteMessage): Completable {
      notificationFactory.createNotification(
            param,
            NotificationFactory.NotificationType.HINT
      )
            .show()
      return Completable.complete()

   }

}