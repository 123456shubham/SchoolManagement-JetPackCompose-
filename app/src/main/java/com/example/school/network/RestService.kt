package com.example.school.network

import android.util.Log
import com.example.school.model.LoginRequest
import com.example.school.model.LoginResponse
import com.example.school.model.addStudent.AddStudentRequest
import com.example.school.model.addStudent.AddStudentResponse
import com.example.school.model.addTeacher.AddTeacherRequest
import com.example.school.model.addTeacher.AddTeacherResponse
import com.example.school.model.post.PostResponse
import com.example.school.sealed.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>

    @POST("api/teacher")
    suspend fun addTeacher(@Body addTeacherRequest:AddTeacherRequest):Response<AddTeacherResponse>

    @POST("auth/create-student")
    suspend fun studentRegister(@Body studentRequest: AddStudentRequest):Response<AddStudentResponse>

    @GET("api/posts")
    suspend fun allPost(@Query("pageNumber") pageNumber:String,
                        @Query("pageSize") pageSize:String,
                        @Query("sortByASC") sortByASC:String?,
                        @Query("sortByDESC") sortByDESC:String?):Response<PostResponse>
}