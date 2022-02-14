package com.example.second_task

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface PersonService{

    fun create(person: Person)

    fun findById(id: Int): Person

    fun findByName(name: String): List<Person>

    fun findByLastname(lastname: String): List<Person>

    fun update(id: Int, person: Person)

    fun delete(id: Int)

}


@Service
class PersonServiceImp(): PersonService{
    @Autowired
    lateinit var personRepository: PersonRepository

    override fun create(person: Person) {
        personRepository.save(person)
    }

    override fun findById(id: Int): Person {
        return personRepository.findByIdOrNull(id)?: throw PersonNotFoundException(id)
    }

    override fun findByName(name: String): List<Person> {
        return personRepository.findAllByName(name)
    }

    override fun findByLastname(lastname: String): List<Person> {
        return personRepository.findAllByLastname(lastname)
    }

    override fun update(id: Int, person: Person) {
        personRepository.save(person)
    }

    override fun delete(id: Int) {
        personRepository.deleteById(id)
    }

}
