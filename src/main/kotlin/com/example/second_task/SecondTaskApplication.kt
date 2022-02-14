package com.example.second_task

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@SpringBootApplication
class SecondTaskApplication

fun main(args: Array<String>) {
    runApplication<SecondTaskApplication>(*args)
}

@RestController
@RequestMapping("/person")
class Controller{

    @Autowired
    lateinit var personService: PersonService


    @PostMapping("")
    fun create(@RequestBody person: Person): ResponseEntity<Person>{

        personService.create(person)
        return ResponseEntity.ok().body(person)


    }

    @GetMapping("id/{id}")
    fun findById(@PathVariable(value = "id") id: Int): ResponseEntity<Person>{

        val person = personService.findById(id)
        return ResponseEntity.ok().body(person)


    }

    @GetMapping("name/{name}")
    fun findByName(@PathVariable(value = "name") name: String): ResponseEntity<List<Person>>{

        val persons = personService.findByName(name)
        return ResponseEntity.ok().body(persons)


    }

    @GetMapping("lastname/{lastname}")
    fun findByLastname(@PathVariable(value = "lastname") lastname: String): ResponseEntity<List<Person>>{

        val persons = personService.findByLastname(lastname)
        return ResponseEntity.ok().body(persons)


    }

    @PutMapping("id/{id}")
    fun update(
        @PathVariable id: Int,
        @Valid @RequestBody person: Person): ResponseEntity<Person>{

        personService.update(id,person)
        return ResponseEntity.ok().body(person)
    }

    @DeleteMapping("id/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String>{

        personService.delete(id)
        return ResponseEntity.ok().body("Entity with id = $id is deleted")


    }

}

