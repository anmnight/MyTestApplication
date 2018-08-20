package com.bankcomm.commlibrary

import com.bankcomm.commlibrary.http.RestClient
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    @Rule
    @JvmField
    val server = MockWebServer()

    private lateinit var service: TestApi

    private fun testJsonStr(): String {
        val books = arrayOf("Java 从入门到放弃", "人工智能从入门到智障")
        val entity = TestEntity("安霄", 18, books.toList())
        return bodyJson(entity)
    }

    private inline fun <reified T> bodyJson(body: T): String = Gson().toJson(body, T::class.java)


    private fun setUpResp() {
        val response = MockResponse().setBody(testJsonStr())
        server.enqueue(response)
    }


    @Before
    fun setUp() {

        val rest = RestClient.Builder().baseUrl(server.url("/").toString()).build()
        service = rest.create(TestApi::class.java)
        setUpResp()
    }


    @Test
    fun apiTest() {
        val version = Version()
        version.message = "test entity"
        version.isStatus = true

        val entity = service.user(1, version).execute().body()
        for (book in entity?.books!!) {
            System.out.println(book)
        }

        server.shutdown()
    }


}