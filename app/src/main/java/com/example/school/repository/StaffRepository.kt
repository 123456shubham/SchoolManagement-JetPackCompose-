package com.example.school.repository

import com.example.school.model.addStudent.AddStudentRequest
import com.example.school.model.addStudent.AddStudentResponse
import com.example.school.model.addTeacher.AddTeacherRequest
import com.example.school.model.addTeacher.AddTeacherResponse
import com.example.school.model.error.MyError
import com.example.school.network.RestService
import com.example.school.sealed.ApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class StaffRepository @Inject constructor(private var restService: RestService) {
    private val _addTeacher= MutableStateFlow<ApiResponse<AddTeacherResponse>>(ApiResponse.Loading())

    val addTeacher: StateFlow<ApiResponse<AddTeacherResponse>>
        get() = _addTeacher

    suspend fun addTeacherApi(addTeacherRequest: AddTeacherRequest){
        // Emit loading State
        _addTeacher.value=ApiResponse.Loading()

        try {

            val response=restService.addTeacher(addTeacherRequest)
            if (response.isSuccessful && response.body()!=null){
                _addTeacher.value=ApiResponse.Success(response.body())
            }else{
                val errorBody=response.errorBody()?.string()
                if (errorBody!=null){
                    val errorResponse:MyError=Gson().fromJson(
                        errorBody,object :TypeToken<MyError>(){}.type
                    )
                    _addTeacher.value=ApiResponse.Error(errorResponse.message)
                }else {
                    _addTeacher.value = ApiResponse.Error(response.message()?:"Something went wrong")
                }
            }
        }catch (e:Exception){
            _addTeacher.value = ApiResponse.Error(e.message ?: "An unexpected error occurred")

        }



    }

    private val _addStudent= MutableStateFlow<ApiResponse<AddStudentResponse>>(ApiResponse.Loading())

    val addStudent:StateFlow<ApiResponse<AddStudentResponse>>
        get()=_addStudent
    suspend fun  addStudentApi(studentRequest: AddStudentRequest){
        _addStudent.value=ApiResponse.Loading()

        try {
            val  response=restService.studentRegister(studentRequest)
            if (response.isSuccessful && response.body()!=null){
                _addStudent.value=ApiResponse.Success(response.body())
            }else{
                val errorBody=response.errorBody()?.string()

                if (errorBody!=null){
                    val errorResponse:MyError=Gson().fromJson(
                        errorBody,object :TypeToken<MyError>(){}.type
                    )
                    _addStudent.value=ApiResponse.Error(errorResponse.message)
                }else{
                    _addStudent.value = ApiResponse.Error(response.message()?:"Something went wrong")

                }
            }
        }catch (e:Exception){
            _addStudent.value = ApiResponse.Error(e.message?:"An unexpected error occurred ")

        }
    }
}