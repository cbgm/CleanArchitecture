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
      prepareDeepLinking()
   }

   private fun resolveDataParts(data: Uri) {
      path = data.path!!
      host = data.host!!
   }

   private fun prepareDeepLinking(): Queue<DeepLink> {
      val splitData = path.split("/")
            .filter { it.isNotEmpty() and it.isNotBlank() }

      var i = 0
      while (i < splitData.size) {
         val deepLink = mapAction(splitData[i])
         if (deepLink.action.hasParameter()) {
            deepLink.parameter = splitData[i + 1]
            ++i
         }
         deepLinks.add(deepLink)
         ++i
      }
      return deepLinks
   }

   private fun mapAction(action: String): DeepLink {
      return DeepLink(registeredDeepLinks[action]!!)
   }
}