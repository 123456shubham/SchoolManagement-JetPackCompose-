package com.example.school.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.school.model.LoginRequest
import com.example.school.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor  (private  val loginRepository: LoginRepository)  :ViewModel() {

    val loginObservable get() = loginRepository.login
    fun loginObserver(loginRequest: LoginRequest){
        viewModelScope.launch {
            loginRepository.loginApi(loginRequest)
        }
    }


}