package com.example.second_task

import javax.persistence.*


@Entity
data class Person(

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    var name: String,

    var lastname: String

)