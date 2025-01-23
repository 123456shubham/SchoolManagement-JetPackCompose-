package com.example.school.model.post

data class PostResponse(
    val lastPage: Boolean?=null,
    val pageElement: Int?=null,
    val pageNumber: Int?=null,
    val pageSize: Int?=null,
    val postList: List<Post>?=null,
    val totalPage: Int?=null
)