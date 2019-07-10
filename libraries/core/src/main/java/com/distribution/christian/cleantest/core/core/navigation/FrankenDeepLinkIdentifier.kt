package com.distribution.christian.cleantest.core.core.navigation

import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkIdentifier


enum class FrankenDeepLinkIdentifier: DeepLinkIdentifier {
   EVENTS {
      override fun hasParameter() = false
   },
   EVENT_DETAIL {
      override fun hasParameter() = true
   },
   SHOP {
      override fun hasParameter() = false
   },
}