package com.example.speedotransfer.ui.viewmodels.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.source.remote.models.AuthData
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterRequest
import com.example.speedotransfer.data.source.remote.models.user.UserLoginRequest
import com.example.speedotransfer.domain.usecases.LoginUserUseCase
import com.example.speedotransfer.domain.usecases.RegisterUserUseCase
import com.example.speedotransfer.domain.usecases.SignOutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class AuthViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val authUiState = _authUiState.asStateFlow()

    fun submitRegistrationData(user: UserAuthRegisterRequest) {
        createUser(user)
    }

    fun submitLoginData(user: UserLoginRequest) {
        loginUser(user)
    }

    private fun createUser(user: UserAuthRegisterRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _authUiState.value = AuthUiState.Loading
            try {
                Log.e("trace", "Submitting user data $user")
                val result = registerUserUseCase.execute(user)
                if (result.isSuccess) {
                    _authUiState.value =
                        AuthUiState.RegistrationSuccess("User created successfully")
                } else {
                    _authUiState.value = AuthUiState.Error("")
                    handleError(Exception(result.exceptionOrNull()?.message))
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun loginUser(user: UserLoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _authUiState.value = AuthUiState.Loading
            try {
                val authDataResult: Result<AuthData> = loginUserUseCase.execute(user)
                if (authDataResult.isSuccess) {
                    _authUiState.value = AuthUiState.LoginSuccess(authDataResult.getOrThrow())
                } else {
                    _authUiState.value = AuthUiState.Error("")
                    handleError(Exception(authDataResult.exceptionOrNull()?.message))
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            _authUiState.value = AuthUiState.Loading
            try {
                val signOutResult = signOutUseCase.execute()
                if (signOutResult.isSuccess) {
                    _authUiState.value = AuthUiState.SignOutSuccess("Sign out successfully")
                } else {
                    _authUiState.value = AuthUiState.Error("")
                    handleError(Exception(signOutResult.exceptionOrNull()?.message))
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleError(exception: Throwable) {
        val errorMessage = when (exception) {
            is UnknownHostException -> "Please check your internet connection."
            else -> exception.message ?: "An unknown error occurred"
        }
        _authUiState.value = AuthUiState.Error(errorMessage)
    }

    fun resetUiState() {
        _authUiState.value = AuthUiState.Idle
    }
}