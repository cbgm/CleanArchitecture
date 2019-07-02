package com.distribution.christian.cleantest.core.core.navigation.deeplink


enum class DeepLinkIdentifier {
   EVENTS {
      override fun hasParameter() = false
   },
   EVENT_DETAIL {
      override fun hasParameter() = true
   },
   SHOP {
      override fun hasParameter() = false
   },
   NONE {
      override fun hasParameter() = false
   };

   abstract fun hasParameter(): Boolean
}