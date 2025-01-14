package com.example.school.repository

import com.example.school.model.error.MyError
import com.example.school.model.post.PostResponse
import com.example.school.network.RestService
import com.example.school.sealed.ApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class HomePageRepository @Inject constructor(private val restService: RestService) {
    val _post= MutableStateFlow<ApiResponse<PostResponse>>(ApiResponse.Loading())
    val post:StateFlow<ApiResponse<PostResponse>>
        get() = _post

    suspend fun postApi(pageNumber: String,pageSize:String,sortByASC:String?,sortByDESC: String?){

        _post.value=ApiResponse.Loading()
        try {

            val response=restService.allPost(pageNumber,pageSize,sortByASC,sortByDESC)
            if (response.isSuccessful && response.body()!=null){
                _post.value = ApiResponse.Success(response.body()!!)

            }else{
                val errorBody=response.errorBody()?.toString()
                if (errorBody!=null){
                    val errorResponse: MyError = Gson().fromJson(
                        errorBody,object : TypeToken<MyError>(){}.type
                    )
                    _post.value=ApiResponse.Error(errorResponse.message)
                }else{
                    _post.value = ApiResponse.Error(response.message()?:"Something went wrong")
                }
            }
        }catch (e:Exception){
            _post.value = ApiResponse.Error(e.message ?: "An unexpected error occurred")

        }
    }
}