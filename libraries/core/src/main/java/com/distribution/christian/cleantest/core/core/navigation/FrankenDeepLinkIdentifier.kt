package com.distribution.christian.cleantest.core.core.navigation

import com.christian.multinavlib.navigation.deeplink.DeepLinkIdentifier


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