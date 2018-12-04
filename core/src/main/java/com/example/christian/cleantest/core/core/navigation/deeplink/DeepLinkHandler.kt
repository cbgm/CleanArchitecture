package com.example.christian.cleantest.core.core.navigation.deeplink

import android.net.Uri
import java.util.LinkedList
import java.util.Queue

class DeepLinkHandler {

   private lateinit var path: String
   private lateinit var host: String
   private var deepLinks: Queue<DeepLink> = LinkedList()

   fun getDeepLink(): DeepLink? = deepLinks.remove()

   fun hasDeepLinks() = deepLinks.isNotEmpty()

   fun setDeepLinks(data: Uri) {
      resolveDataParts(data)
      prepareDeeplinking()
   }

   private fun resolveDataParts(data: Uri) {
      path = data.path!!
      host = data.host!!
   }

   private fun prepareDeeplinking(): Queue<DeepLink> {
      deepLinks.add(DeepLink(mapAction(host), null))
      val splitData = path.split("/")
            .filter { it.isNotEmpty() and it.isNotBlank() }

      for (i in 0 until splitData.size - 1) {

         if (i + 1 == splitData.size - 1) {
            deepLinks.add(DeepLink(mapAction(splitData[i]), splitData[i + 1]))
            return deepLinks
         } else {
            deepLinks.add(DeepLink(mapAction(splitData[i]), null))
         }
      }
      return deepLinks
   }

   private fun mapAction(action: String): DeepLinkIdentifier {
      return when(action){
         "cart" -> DeepLinkIdentifier.CART
         "shop" -> DeepLinkIdentifier.SHOP
         "detail" -> DeepLinkIdentifier.DETAIL
         else -> DeepLinkIdentifier.NONE
      }
   }

   enum class DeepLinkIdentifier{
      CART,
      DETAIL,
      SHOP,
      NONE
   }
}