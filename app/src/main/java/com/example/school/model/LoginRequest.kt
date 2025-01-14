package com.example.school.model

data class LoginRequest(
    val loginType: String?,
    val password: String?,
    val username: String?
)