package com.distribution.christian.cleantest.core.core.mock

import com.distribution.christian.cleantest.core.data.model.EventDto
import okhttp3.*
import java.io.IOException
import com.google.gson.GsonBuilder

class FakeInterceptor : Interceptor {

   private var events = ArrayList<EventDto>()

   init {
      /*events.add(
            EventDto(
                  0,
                  "Wild PartyHell in Buku",
                  "Burgkunstadt",
                  location,
                  date,
                  time,
                  price,
                  description
            )
      )
      events.add(
            EventDto(
                  1,
                  "Wild PartyHell in Bamberg",
                  "Bamberg",
                  location,
                  date,
                  time,
                  price,
                  description
            )
      )
      events.add(
            EventDto(
                  2,
                  "Wild PartyHell in Coburg",
                  "Coburg",
                  location,
                  date,
                  time,
                  price,
                  description
            )
      )
      events.add(
            EventDto(
                  3,
                  "Wild PartyHell in Neustadt",
                  "Neustadt",
                  location,
                  date,
                  time,
                  price,
                  description
            )
      )
      events.add(
            EventDto(
                  4,
                  "Wild PartyHell in Nürnberg",
                  "Nürnberg",
                  location,
                  date,
                  time,
                  price,
                  description
            )
      )
      events.add(
            EventDto(
                  5,
                  "Wild PartyHell in Altenkusntadt",
                  "Altenkunstadt",
                  location,
                  date,
                  time,
                  price,
                  description
            )
      )
      events.add(
            EventDto(
                  6,
                  "Wild PartyHell in Lif",
                  "Lichtenfels",
                  location,
                  date,
                  time,
                  price,
                  description
            )
      )*/
      events.addAll(EventGenerator.generate(10))

   }


   @Throws(IOException::class)
   override fun intercept(chain: Interceptor.Chain): Response? {
      val response: Response?

      val url: String = chain.request()
            .url()
            .url()
            .toString()
      val responseString: String = if (url.contains("events")) {
         Thread.sleep(2000)
         getMockedEventsJson()
      } else
         getMockedEventJson(chain.request().url().queryParameter("eventId")!!)
      response = Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                  ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                  )
            )
            .addHeader("content-type", "application/json")
            .build()


      return response
   }


   private fun getMockedEventJson(id: String): String {

      return GsonBuilder().create()
            .toJson(events.first { it.id.toString() == id })
   }

   private fun getMockedEventsJson(): String {

      return GsonBuilder().create()
            .toJson(events)
   }

   companion object {
      private const val description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
      private const val location = "Cityhall, Street 12"
      private const val date = "Thursday 13.09.2017"
      private const val time = "1pm - 5pm"
      private const val price = "13 Euro"


   }

}