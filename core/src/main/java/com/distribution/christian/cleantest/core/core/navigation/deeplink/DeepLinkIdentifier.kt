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
         return NONE
      }
   },
   SHOP {
      override fun mapAction(action: String): DeepLinkIdentifier {
         return NONE
      }
   },
   NONE {
      override fun mapAction(action: String): DeepLinkIdentifier {
         return NONE
      }
   };

   abstract fun mapAction(action: String): DeepLinkIdentifier
}