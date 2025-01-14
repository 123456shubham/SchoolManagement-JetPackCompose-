package com.example.school.model.post

data class PostResponse(
    val lastPage: Boolean?,
    val pageElement: Int?,
    val pageNumber: Int?,
    val pageSize: Int?,
    val postList: List<Post?>?,
    val totalPage: Int?
)