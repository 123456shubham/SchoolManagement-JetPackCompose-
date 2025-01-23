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
    var _post= MutableStateFlow<PostResponse>(PostResponse())
    val post:StateFlow<PostResponse> = _post

    suspend fun postAPI(pageNumber:String,pageNo:String,sortByASC:String?,sortByDESC:String?){
        try {
            val response=restService.allPost(pageNumber, pageSize = pageNo,sortByASC,sortByDESC)
            if (response.isSuccessful && response.body()!=null){
                _post.emit(response.body()!!)
            }
        }catch (e:Exception){

        }
    }
}