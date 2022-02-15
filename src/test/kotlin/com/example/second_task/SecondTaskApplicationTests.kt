package com.example.second_task


import com.mashape.unirest.http.Unirest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertEquals
import java.text.SimpleDateFormat
import java.util.*


@SpringBootTest
class SecondTaskApplicationTests {

    val sdf = SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
    val currentData = sdf.format(Date())
    val testName = "Test_Name_$currentData"
    val testLastName = "Test_Lastname_$currentData"
    val postRequestEntity = "{\r\n    \"id\":666,\r\n    \"name\":\"$testName\",\r\n    \"lastname\":\"$testLastName\"\r\n}"



    @Test
    fun createEntityTest() {

        val expectedResponse = "{\"id\":666,\"name\":\"$testName\",\"lastname\":\"$testLastName\"}"

        Unirest.setTimeouts(0, 0)
        val postResponse: String = Unirest.post("http://localhost:8080/person")
            .header("Content-Type", "application/json")
            .body(postRequestEntity)
            .asString()
            .body
            .toString()


        assertEquals(expectedResponse,postResponse)

    }


    @Test
    fun readEntityByIdTest() {

        val expectedResponse = "{\"id\":666,\"name\":\"$testName\",\"lastname\":\"$testLastName\"}"

        val getResponse: String = Unirest.get("http://localhost:8080/person/id/666")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()

        println(getResponse)

        assertEquals(expectedResponse, getResponse)

    }

    @Test
    fun readEntityByNameTest() {

        val expectedResponse = "{\"id\":666,\"name\":\"$testName\",\"lastname\":\"$testLastName\"}"

        val getResponse: String = Unirest.get("http://localhost:8080/person/name/$testName")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()

        assertEquals(expectedResponse,getResponse)

    }

    @Test
    fun readEntityByLastnameTest() {

        val expectedResponse = "{\"id\":666,\"name\":\"$testName\",\"lastname\":\"$testLastName\"}"

        val getResponse: String = Unirest.get("http://localhost:8080/person/name/$testLastName")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()

        assertEquals(expectedResponse,getResponse)

    }



    @Test
    fun updateEntityTest() {

        val anotherRequestEntity = "{\r\n    \"id\":\"666\",\r\n    \"name\":\"Boris\",\r\n    \"lastname\":\"Schukin\"\r\n}"
        val expectedResponse = "{\"id\":666,\"name\":\"Boris\",\"lastname\":\"Schukin\"}"

        Unirest.setTimeouts(0, 0)
        val putResponse: String = Unirest.put("http://localhost:8080/person")
            .header("Content-Type", "application/json")
            .body(anotherRequestEntity)
            .asString()
            .body
            .toString()


        assertEquals(expectedResponse, putResponse)
    }

    @Test
    fun DeleteEntityTest() {


        val expectedResponse = "Entity with id = 666 is deleted"

        Unirest.setTimeouts(0, 0)


        val deleteResponse: String = Unirest.delete("http://localhost:8080/person/id/666")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()

        assertEquals(expectedResponse, deleteResponse)
    }

}
