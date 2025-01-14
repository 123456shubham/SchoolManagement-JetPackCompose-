package com.example.school.model

data class LoginResponse(
    val rollNo: String?=null,
    val token: String?=null,
    val success:Boolean?=null,
    val message:String?=null
)