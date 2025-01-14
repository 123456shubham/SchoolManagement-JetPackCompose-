package com.example.school.model.addTeacher

data class AddTeacherRequest(
    val classTeacher: String?,
    val dob: String?,
    val gender: String?,
    val name: String?,
    val password: String?,
    val phone: String?,
    val subjectName: String?,
    val url: String?
)