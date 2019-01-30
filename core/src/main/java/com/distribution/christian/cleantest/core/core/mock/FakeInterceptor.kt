package com.distribution.christian.cleantest.core.core.mock

import com.distribution.christian.cleantest.core.data.model.SearchDto
import com.distribution.christian.cleantest.core.data.model.UserDto
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import com.google.gson.GsonBuilder
import okio.Buffer

class FakeInterceptor : Interceptor {

   private companion object {
      //var events = ArrayList<com.distribution.christian.cleantest.event.data.model.EventDto>()
      lateinit var userDto: UserDto
      var searchDtos = ArrayList<SearchDto>()
   }

   init {
      //events.addAll(EventGenerator.generate(10))
      userDto = UserDto(
         firstName = "Christian",
         lastName = "Bergmann",
         birthDate = "12.09.2104",
         email = "test@test.de",
         password = "asdad",
         alias = "cb2019"
      )

      searchDtos.add(
            SearchDto(
                  userId = "cb2019",
                  city = "Lichtenfeks",
                  distance = 20,
                  maxPrice = 13,
                  type = "empty"
            )
      )

      searchDtos.add(
            SearchDto(
                  userId = "klk",
                  city = "Burgkunstadt",
                  distance = 20,
                  maxPrice = 10,
                  type = "empty"
            )
      )

   }


   @Throws(IOException::class)
   override fun intercept(chain: Interceptor.Chain): Response? {
      val segements = chain.request()
            .url()
            .pathSegments()
      val response: Response?

      val url: String = chain.request()
            .url()
            .url()
            .toString()

      var code = 200

      val responseString: String = when (chain.request().method()) {
         "GET" -> {
            if (url.contains("authenticate")) {
               Thread.sleep(2000)
               getMockedUserJson()

            } else {
               getMockedSearchJson(segements[1])
            }
         }
         "PUT" -> {
            updateMockedUserJson(requestBodyToString(chain.request().body()!!))
         }
         else -> ""
      }

      response = Response.Builder()
            .code(code)
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


   /*private fun getMockedEventJson(id: String): String {

      return GsonBuilder().create()
            .toJson(events.first { it.id.toString() == id })
   }*/

   /*private fun updateMockedEventJson(json: String): String {
      val event = Gson().fromJson(json, com.distribution.christian.cleantest.event.data.model.EventDto::class.java)
      var updatedEvent: com.distribution.christian.cleantest.event.data.model.EventDto? = null
      events.forEach {
         if (it.id == event.id) {
            it.isStarred = !event.isStarred
            updatedEvent = it
         }
      }
      return GsonBuilder().create()
            .toJson(updatedEvent)

   }*/

   /*private fun getMockedEventsJson(): String {

      return GsonBuilder().create()
            .toJson(events)
   }*/

   private fun updateMockedUserJson(json: String): String {
      val tempUser = Gson().fromJson(json, UserDto::class.java)
      userDto = tempUser
      return GsonBuilder().create().toJson(userDto)
   }

   private fun getMockedUserJson(): String {
      return GsonBuilder().create().toJson(userDto)
   }

   private fun getMockedSearchJson(id: String): String {
      return GsonBuilder().create()
            .toJson(searchDtos.first { it.userId == id })
   }

   fun requestBodyToString(requestBody: RequestBody): String {
      return try {
         val buffer = Buffer()
         requestBody.writeTo(buffer)
         buffer.readUtf8()
      } catch (e: Exception) {
         e.printStackTrace()
         ""
      }
   }

   /*companion object {
      private const val description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
      private const val location = "Cityhall, Street 12"
      private const val date = "Thursday 13.09.2017"
      private const val time = "1pm - 5pm"
      private const val price = "13 Euro"


   }*/

}