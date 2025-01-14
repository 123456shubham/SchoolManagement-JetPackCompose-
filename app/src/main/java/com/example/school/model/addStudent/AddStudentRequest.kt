package com.example.school.model.addStudent

data class AddStudentRequest(
    val age: String?=null,
    val className: String?=null,
    val fatherMobile: String?=null,
    val fatherName: String?=null,
    val gender: String?=null,
    val img: String?=null,
    val isTransportAvailable: Boolean?=null,
    val loginType: String?=null,
    val motherMobile: String?=null,
    val motherName: String?=null,
    val name: String?=null,
    val password: String?=null,
    val rollNo: String?=null
)