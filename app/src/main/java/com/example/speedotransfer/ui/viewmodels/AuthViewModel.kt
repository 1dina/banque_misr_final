package com.example.speedotransfer.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.models.AuthData
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.domain.usecases.LoginUserUseCase
import com.example.speedotransfer.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase) : ViewModel() {
    private val _userRegistrationData = MutableStateFlow(UserAuthRegisterRequest())
    private val userRegistrationData = _userRegistrationData.asStateFlow()
    private val _userLoginData = MutableStateFlow(UserLoginRequest())
    private val userLoginData = _userLoginData.asStateFlow()
    private val _responseStatus = MutableStateFlow<Boolean?>(null)
    val responseStatus = _responseStatus.asStateFlow()

    fun submitRegistrationData(user: UserAuthRegisterRequest) {
        _userRegistrationData.value = user
        createUser(userRegistrationData.value)
    }
    fun submitLoginData(user: UserLoginRequest) {
        _userLoginData.value = user
        loginUser(userLoginData.value)
    }


    private fun createUser(user: UserAuthRegisterRequest) {
        viewModelScope.launch {
            try {
                Log.e("trace", "user data $user")
                val result = registerUserUseCase.execute(user)
                if (result.isSuccess) {
                    Log.e("trace", "User created successfully")
                    _responseStatus.value = true
                } else {
                    Log.e("trace", "Error: ${result.exceptionOrNull()?.message}")
                    _responseStatus.value = false
                }
            } catch (e: Exception) {
                Log.e("trace", "Network error: ${e.message}")
                _responseStatus.value = false
            }
        }
    }
    private fun loginUser(user: UserLoginRequest) {
        viewModelScope.launch {
            try {
                val authData: AuthData = loginUserUseCase.execute(user)
                Log.e("trace" , "userLogin is successed ${authData.id}")
                _responseStatus.value = true


            } catch (e: Exception) {
                // Handle login error
              //  println("Login failed: ${e.message}")
                _responseStatus.value = false
                Log.e("trace" , "userLogin is failed ${e.message}")
            }
        }
    }
    fun resetResponseStatus() {
        _responseStatus.value = null
    }
}