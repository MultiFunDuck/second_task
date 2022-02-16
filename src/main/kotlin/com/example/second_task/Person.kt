package com.example.second_task

import javax.persistence.*


@Entity
class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    var name: String,

    var lastname: String

)