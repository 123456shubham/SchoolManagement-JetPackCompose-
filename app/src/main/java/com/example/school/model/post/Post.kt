package com.example.school.model.post

data class Post(
    val category: Category?,
    val comments: List<Any?>?,
    val content: String?,
    val date: String?,
    val id: Int?,
    val imageName: String?,
    val studentRegister: StudentRegister?,
    val title: String?
)