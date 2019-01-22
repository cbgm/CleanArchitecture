package com.distribution.christian.cleantest.core.core.navigation.deeplink

data class DeepLink(
      var action: DeepLinkHandler.DeepLinkIdentifier,
      var parameter: String? = null
)