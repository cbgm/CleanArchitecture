package com.distribution.christian.cleantest.core.core.navigation.deeplink

import android.net.Uri
import java.util.LinkedList
import java.util.Queue


class DeepLinkHandler {

   private lateinit var path: String
   private lateinit var host: String
   private var deepLinks: Queue<DeepLink> = LinkedList()
   private val registeredDeepLinks = HashMap<String, DeepLinkIdentifier>()

   fun getDeepLink(): DeepLink? = deepLinks.remove()

   fun hasDeepLinks() = deepLinks.isNotEmpty()

   fun registerDeepLink(uriPart: String, identifier: DeepLinkIdentifier) {
      this.registeredDeepLinks[uriPart] = identifier
   }

   fun setUri(data: Uri) {
      resolveDataParts(data)
      prepareDeeplinking()
   }

   private fun resolveDataParts(data: Uri) {
      path = data.path!!
      host = data.host!!
   }

   private fun prepareDeeplinking(): Queue<DeepLink> {
      val splitData = path.split("/")
            .filter { it.isNotEmpty() and it.isNotBlank() }

      for (i in 0 until splitData.size) {
         val deepLink = mapAction(splitData[i])
         if (deepLink.action.hasParameter()) {
            deepLink.parameter = splitData[i + 1]
         }
         deepLinks.add(deepLink)
      }
      return deepLinks
   }

   private fun mapAction(action: String): DeepLink {

      registeredDeepLinks[action]?.let {
         return DeepLink(it)
      } ?: run {
         return DeepLink(DeepLinkIdentifier.NONE)
      }
   }
}