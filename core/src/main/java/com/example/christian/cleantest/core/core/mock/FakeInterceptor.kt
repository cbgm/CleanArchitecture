package com.example.christian.cleantest.core.core.mock

import com.example.christian.cleantest.core.data.model.CartDto
import com.example.christian.cleantest.core.data.model.UserDto
import okhttp3.*
import java.io.IOException
import com.google.gson.GsonBuilder

class FakeInterceptor : Interceptor {

    private var users = ArrayList<UserDto>()
    private var userCart = HashMap<String, CartDto>()

    init{
        users.add(UserDto("bla", "blub"))
        users.add(UserDto("bl", "blo"))

        for(user in users)
            userCart[user.name] = CartDto(
                  50 + user.name.length,
                  listOf(
                        "item1",
                        "item2"
                  )
            )

    }


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val response: Response?

        val url:String = chain.request().url().url().toString()
        val responseString: String = if(url.contains("users"))
            getMockedUserJson()
        else
            getMockedCartJson(chain.request().url().queryParameter("userId")!!)
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


    private fun getMockedCartJson(id: String): String {

        return GsonBuilder().create().toJson(userCart[id])
    }

    private fun getMockedUserJson(): String{

        return GsonBuilder().create().toJson(users)
    }

}