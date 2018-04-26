package com.example.christian.cleantest.core.mock

import com.example.christian.cleantest.data.model.UserDto
import okhttp3.*
import java.io.IOException
import com.google.gson.GsonBuilder

class FakeInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val response: Response?
        val responseString: String = getMockedJson()
        response = Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()


        return response
    }

    private fun getMockedJson(): String{
        val list = ArrayList<UserDto>()
        list.add(UserDto("bla", "blub"))
        list.add(UserDto("bl", "blo"))

        return GsonBuilder().create().toJson(list)
    }

}