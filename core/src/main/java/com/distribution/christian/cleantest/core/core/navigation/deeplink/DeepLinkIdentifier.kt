package com.distribution.christian.cleantest.core.core.navigation.deeplink

enum class DeepLinkIdentifier {
   EVENTS {
      override fun mapAction(action: String): DeepLinkIdentifier {
         return when (action) {
            "detail" -> DeepLinkIdentifier.EVENT_DETAIL
            else -> NONE
         }
      }
   },
   EVENT_DETAIL {
      override fun mapAction(action: String): DeepLinkIdentifier {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }
   },
   SHOP {
      override fun mapAction(action: String): DeepLinkIdentifier {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }
   },
   NONE {
      override fun mapAction(action: String): DeepLinkIdentifier {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }
   };

   abstract fun mapAction(action: String): DeepLinkIdentifier
}