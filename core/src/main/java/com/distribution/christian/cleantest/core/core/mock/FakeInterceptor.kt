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
      lateinit var userDto: UserDto
      var searchDtos = ArrayList<SearchDto>()
   }

   init {
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
               Thread.sleep(1000)
               getMockedUserJson()

            } else {
               getMockedSearchJson(segements[1])
            }
         }
         "PUT" -> {
            if (url.contains("authenticate")) {
               updateMockedUserJson(requestBodyToString(chain.request().body()!!))
            } else {
               updateMockedSearchJson(requestBodyToString(chain.request().body()!!))
            }
         }
         else -> {
            code = 500
            ""
         }
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

   private fun updateMockedUserJson(json: String): String {
      val tempUser = Gson().fromJson(json, UserDto::class.java)
      userDto = tempUser
      return GsonBuilder().create()
            .toJson(userDto)
   }

   private fun updateMockedSearchJson(json: String): String {
      val tempSearch = Gson().fromJson(json, SearchDto::class.java)
      var updatedSearch: SearchDto? = null
      searchDtos.forEach {
         if (it.userId == tempSearch.userId) {
            updatedSearch = it
         }
      }
      return GsonBuilder().create()
            .toJson(updatedSearch)
   }

   private fun getMockedUserJson(): String {
      return GsonBuilder().create()
            .toJson(userDto)
   }

   private fun getMockedSearchJson(id: String): String {
      return GsonBuilder().create()
            .toJson(searchDtos.first { it.userId == id })
   }

   private fun requestBodyToString(requestBody: RequestBody): String {
      return try {
         val buffer = Buffer()
         requestBody.writeTo(buffer)
         buffer.readUtf8()
      } catch (e: Exception) {
         e.printStackTrace()
         ""
      }
   }
}