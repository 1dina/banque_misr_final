package com.example.speedotransfer.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.domain.usecases.AuthenticateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authenticateUserUseCase: AuthenticateUserUseCase) : ViewModel() {
    private val _userData = MutableStateFlow(UserAuthRegisterRequest())
    val userData = _userData.asStateFlow()
    private val _responseStatus = MutableStateFlow(false)
    val responseStatus = _responseStatus.asStateFlow()

    fun submitUserData(user: UserAuthRegisterRequest) {
        _userData.value = user
        createUser(userData.value)
    }

    private fun createUser(user: UserAuthRegisterRequest) {
        viewModelScope.launch {
            try {
                Log.e("trace", "user data $user")
                val result = authenticateUserUseCase.execute(user)
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
    fun resetResponseStatus() {
        _responseStatus.value = false
    }
}