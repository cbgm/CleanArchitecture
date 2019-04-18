package com.distribution.christian.cleantest.core.core.util.network

import com.distribution.christian.cleantest.core.core.util.listener.NetworkListener
import org.koin.standalone.KoinComponent

class NetworkReceiverManager {
   private val receivers = HashMap<String, NetworkListener>()

   fun notifyReceivers() {
      receivers.forEach{it.value.networkUnavailable()}
   }

   fun registerReceiver(identifier: String, networkListener: NetworkListener) {
      receivers.put(identifier, networkListener)
   }

   fun unregisterReceiver(identifier: String) {
      receivers.remove(identifier)
   }
}