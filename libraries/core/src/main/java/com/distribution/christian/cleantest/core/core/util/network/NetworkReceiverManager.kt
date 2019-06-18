package com.distribution.christian.cleantest.core.core.util.network

import com.distribution.christian.cleantest.core.core.util.listener.NetworkListener
import org.koin.standalone.KoinComponent

class NetworkReceiverManager {
   private val receivers = HashMap<String, NetworkListener>()

   fun notifyReceiversAvailable() {
      receivers.forEach{it.value.networkAvailable()}
   }

   fun notifyReceiversUnavailable() {
      receivers.forEach{it.value.networkUnavailable()}
   }

   fun registerReceiver(identifier: String, networkListener: NetworkListener) {
      receivers[identifier] = networkListener
   }

   fun unregisterReceiver(identifier: String) {
      receivers.remove(identifier)
   }

   fun clearReceivers() = receivers.clear()
}