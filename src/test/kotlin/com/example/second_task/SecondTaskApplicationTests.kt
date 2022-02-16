package com.example.second_task

import com.mashape.unirest.http.Unirest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat
import java.util.*


@SpringBootTest
class SecondTaskApplicationTests {

    @Autowired
    lateinit var personRepository: PersonRepository

    val currentData = SimpleDateFormat("dd_M_yyyy_hh_mm_ss").format(Date())
    var testId = -1
    val testName = "Test_Name_$currentData"
    val testLastName = "Test_Lastname_$currentData"
    var testPerson = Person(testId,testName,testLastName)

    @BeforeEach
    fun init(){
        println("initializing test")
        personRepository.save(testPerson)
        testId = personRepository.findAllByName(testName)[0].id
        println("Testing person with id = $testId, name = $testName")
    }

    @AfterEach
    fun execute(){
        println("deleting test person with id = $testId")
        personRepository.deleteById(testId)
        println("exiting from test")
    }


    @Test
    fun createTest(){
        println("We're in creation test")

        val currentData = SimpleDateFormat("dd_M_yyyy_hh_mm_ss").format(Date())
        var creatingTestId = testId + 1
        val creatingTestName = "Creating_Test_Name_$currentData"
        val creatingTestLastName = "Creating_Test_Lastname_$currentData"

        val postRequestEntity = "{\r\n    \"id\":\"$creatingTestId\"" +
                                ",\r\n    \"name\":\"$creatingTestName\"," +
                                "\r\n    \"lastname\":\"$creatingTestLastName\"\r\n}"



        val expectedResponse = "{\"id\":$creatingTestId," +
                                "\"name\":\"$creatingTestName\"," +
                                "\"lastname\":\"$creatingTestLastName\"}"

        Unirest.setTimeouts(0, 0)
        val postResponse: String = Unirest.post("http://localhost:8080/person")
            .header("Content-Type", "application/json")
            .body(postRequestEntity)
            .asString()
            .body
            .toString()



        Assertions.assertEquals(expectedResponse, postResponse)

        personRepository.deleteById(creatingTestId)
    }


    @Test
    fun readByIdTest(){

        println("We're in read by id test")

        val expectedResponse = "{\"id\":$testId," +
                "\"name\":\"$testName\"," +
                "\"lastname\":\"$testLastName\"}"

        Unirest.setTimeouts(0, 0)
        val getResponse: String = Unirest.get("http://localhost:8080/person/id/$testId")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()


        Assertions.assertEquals(expectedResponse, getResponse)
    }

    @Test
    fun readByNameTest(){

        println("We're in read by name test")

        val expectedResponse = "[{\"id\":$testId," +
                "\"name\":\"$testName\"," +
                "\"lastname\":\"$testLastName\"}]"

        Unirest.setTimeouts(0, 0)
        val getResponse: String = Unirest.get("http://localhost:8080/person/name/$testName")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()


        Assertions.assertEquals(expectedResponse, getResponse)
    }

    @Test
    fun readByLastnameTest(){

        println("We're in read by lastname test")

        val expectedResponse = "[{\"id\":$testId," +
                "\"name\":\"$testName\"," +
                "\"lastname\":\"$testLastName\"}]"

        Unirest.setTimeouts(0, 0)
        val getResponse: String = Unirest.get("http://localhost:8080/person/lastname/$testLastName")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()


        Assertions.assertEquals(expectedResponse, getResponse)
    }


    @Test
    fun updateTest(){

        println("We're in update test. Id=$testId")

        val expectedResponse = "{\"id\":$testId," +
                "\"name\":\"Boris\"," +
                "\"lastname\":\"Schukin\"}"


        val postRequestEntity = "{\r\n    \"id\": $testId," +
                "\r\n    \"name\":\"Boris\"," +
                "\r\n    \"lastname\":\"Schukin\"\r\n}"



        Unirest.setTimeouts(0, 0)
        val getResponse: String = Unirest.put("http://localhost:8080/person/id/$testId")
            .header("Content-Type", "application/json")
            .body(postRequestEntity)
            .asString()
            .body
            .toString()


        println("before assertion")
        Assertions.assertEquals(expectedResponse, getResponse)
        println("after assertion")
    }


    @Test
    fun deleteEntityTest() {


        var deletingTestId = testId + 1
        val deletingTestName = "Deleting_Test_Name_$currentData"
        val deletingTestLastName = "Deleting_Test_Lastname_$currentData"

        personRepository.save(Person(deletingTestId,deletingTestName,deletingTestLastName))


        val expectedResponse = "Entity with id = $deletingTestId is deleted"

        Unirest.setTimeouts(0, 0)


        val deleteResponse: String = Unirest.delete("http://localhost:8080/person/id/$deletingTestId")
            .header("Content-Type", "application/json")
            .asString()
            .body
            .toString()

        Assertions.assertEquals(expectedResponse, deleteResponse)
    }

}