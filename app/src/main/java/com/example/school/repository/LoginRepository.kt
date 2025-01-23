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
    private val _loginState = MutableStateFlow<LoginResponse>(LoginResponse()) // Initial state
    val login: StateFlow<LoginResponse> = _loginState

    suspend fun loginApi(loginRequest: LoginRequest) {
        try {
            val response = restService.login(loginRequest)

            if (response.isSuccessful && response.body() != null) {
                // Emit the successful response body
                _loginState.emit(response.body()!!)
            } else {
                // Handle the error response
                val errorBody = response.errorBody()?.string()
                if (!errorBody.isNullOrEmpty()) {
                    try {
                        // Parse the error response
                        val errorResponse: MyError = Gson().fromJson(
                            errorBody,
                            object : TypeToken<MyError>() {}.type
                        )

                    } catch (jsonException: Exception) {

                    }
                } else {

                }
            }
        } catch (e: Exception) {
//            _loginState.emit(e.message ?: "An unexpected error occurred. Please try again.")
        }
    }






}
