package com.example.second_task

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class PersonNotFoundException(id: Int): RuntimeException("Person with id = $id is not found")




