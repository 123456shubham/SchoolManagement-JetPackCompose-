package com.example.school.sealed
sealed interface ApiResponse<T> {
    val data: T?
    val errorMessage: String?

    // Loading can work with any type, so we define it as a class with a generic parameter
    class Loading<T> : ApiResponse<T> {
        override val data: T? = null
        override val errorMessage: String? = null
    }

    data class Success<T>(override val data: T?) : ApiResponse<T> {
        override val errorMessage: String? = null
    }

    data class Error<T>(override val errorMessage: String) : ApiResponse<T> {
        override val data: T? = null
    }
}
