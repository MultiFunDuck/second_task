package com.example.second_task

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class SecondTaskApplication

fun main(args: Array<String>) {
    runApplication<SecondTaskApplication>(*args)
}

@Repository
interface PersonRepository : CrudRepository<Person, Int>



@RestController
@RequestMapping("/manage")
class PersonController{

    @Autowired
    private lateinit var personRepository: PersonRepository


    @GetMapping("/persons")
    fun getAllPersons(): MutableIterable<Person> {
        return personRepository.findAll()
    }



    @PostMapping("/persons")
    fun createPerson(@RequestBody person: Person): Person{
        return personRepository.save(person)
    }


    @GetMapping("/persons/{id}")
    fun getPersonById(@PathVariable(value = "id") id: Int): ResponseEntity<Person>{
        val person = personRepository.findById(id).orElseThrow()
        return ResponseEntity.ok().body(person)
    }

    @PutMapping("/persons/{id}")
    fun updatePerson(@PathVariable(value = "id") id: Int, @RequestBody personDetails:Person): ResponseEntity<Person>{
        val person = personRepository.findById(id).orElseThrow()
        person.name = personDetails.name
        person.lastname = personDetails.lastname
        personRepository.save(person)

        return ResponseEntity.ok().body(person)
    }

    @DeleteMapping("/persons/{id}")
    fun deletePerson(@PathVariable(value = "id") id: Int): ResponseEntity<Person>{
        personRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }
}

/*

@RestController
@RequestMapping("/person")
class CreateController{

    @Autowired
    private lateinit var personRepository: PersonRepository

    @PostMapping("/person/add")
    fun createPerson(@RequestBody person: Person): Person{
        return personRepository.save(person)
    }

}


@RestController
@RequestMapping("/person")
class ReadController{

    @Autowired
    private lateinit var personRepository: PersonRepository

    @GetMapping("/person/{id}")
    fun getPersonById(@PathVariable(value = "id") id: Int): ResponseEntity<Person>{
        val person = personRepository.findById(id).orElseThrow()
        return ResponseEntity.ok().body(person)
    }


    @GetMapping("/person/{name}")
    fun getAllPersonByName(@PathVariable(value = "name") name: String): ResponseEntity<List<Person>>{
        val persons = personRepository.
        return ResponseEntity.ok().body(persons)
    }

    @GetMapping("/person/{lastname}")
    fun getAllPersonByLastname(@PathVariable(value = "lastname") lastname: String): ResponseEntity<List<Person>>{
        val persons = personRepository.findAll()
        return ResponseEntity.ok().body(persons)
    }

}
*/
