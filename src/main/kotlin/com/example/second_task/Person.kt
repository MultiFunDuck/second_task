package com.example.second_task

import javax.persistence.*


@Entity
@Table(name = "person")
open class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Int,

    @Column(name = "name",nullable = false)
    open var name: String,

    @Column(name = "lastname",nullable = false)
    open var lastname: String

)