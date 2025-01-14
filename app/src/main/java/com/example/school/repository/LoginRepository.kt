package com.example.school.repository

import com.example.school.model.LoginRequest
import com.example.school.model.LoginResponse
import com.example.school.model.error.MyError
import com.example.school.network.RestService
import com.example.school.sealed.ApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject



class LoginRepository @Inject constructor(private val restService: RestService) {

    private val _loginState = MutableStateFlow<ApiResponse<LoginResponse>>(ApiResponse.Loading())

    val login: StateFlow<ApiResponse<LoginResponse>>
        get() = _loginState

    suspend fun loginApi(loginRequest: LoginRequest) {
        _loginState.value = ApiResponse.Loading()


        try {
            val response = restService.login(loginRequest)
            if (response.isSuccessful && response.body() != null) {
                _loginState.value = ApiResponse.Success(response.body()!!) // Successful login response
            } else {
                val errorBody=response.errorBody()?.toString()

                if (errorBody!=null){
                    val errorResponse:MyError=Gson().fromJson(
                        errorBody,object :TypeToken<MyError>(){}.type
                    )
                    _loginState.value=ApiResponse.Error(errorResponse.message)
                }else{
                    _loginState.value = ApiResponse.Error(response.message()?:"Something went wrong")

                }


            }
        } catch (e: Exception) {
            _loginState.value = ApiResponse.Error(e.message ?: "An unexpected error occurred")
        }
    }
}
