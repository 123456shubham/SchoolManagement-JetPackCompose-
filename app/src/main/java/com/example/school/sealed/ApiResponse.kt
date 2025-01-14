package com.example.school.sealed

import com.example.school.model.LoginResponse

 sealed interface ApiResponse <out T>{
     val data:T?
     val errorMessage:String?
    class Loading<T> : ApiResponse<T>{
        override val data:T?=null
        override val errorMessage:String?=null
    }
     class Success<T>(override val data: T?) :ApiResponse<T>{
        override val errorMessage:String?=null
    }
     class Error<T>( override val errorMessage: String?) : ApiResponse<T>{
         override val data: T?=null
    }

}