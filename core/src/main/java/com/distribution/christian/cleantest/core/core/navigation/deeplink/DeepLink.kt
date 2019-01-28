package com.distribution.christian.cleantest.core.core.navigation.deeplink

data class DeepLink(
      var action: DeepLinkIdentifier,
      var parameter: String? = null
)