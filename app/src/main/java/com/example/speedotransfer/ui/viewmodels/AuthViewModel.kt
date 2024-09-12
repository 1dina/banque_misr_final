package com.example.speedotransfer.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.models.AuthData
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.domain.usecases.LoginUserUseCase
import com.example.speedotransfer.domain.usecases.RegisterUserUseCase
import com.example.speedotransfer.domain.usecases.SignOutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class AuthViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    private val _userRegistrationData = MutableStateFlow(UserAuthRegisterRequest())
    private val userRegistrationData = _userRegistrationData.asStateFlow()
    private val _userLoginData = MutableStateFlow(UserLoginRequest())
    private val userLoginData = _userLoginData.asStateFlow()
    private val _responseStatus = MutableStateFlow<Boolean?>(null)
    val responseStatus = _responseStatus.asStateFlow()
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage = _toastMessage.asStateFlow()

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
                Log.e("trace", "Submitting user data $user")
                val result = registerUserUseCase.execute(user)
                if (result.isSuccess) {
                    _toastMessage.value = "User created successfully"
                    _responseStatus.value = true
                } else {
                    handleError(Exception(result.exceptionOrNull()?.message) )
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun loginUser(user: UserLoginRequest) {
        viewModelScope.launch {
            try {
                val authDataResult: Result<AuthData> = loginUserUseCase.execute(user)
                if (authDataResult.isSuccess) {
                    _responseStatus.value = true
                    _toastMessage.value = "Login successful"
                } else {
                    handleError(Exception(authDataResult.exceptionOrNull()?.message) )
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }
     fun signOut(){
        viewModelScope.launch {
            try {
                val signOutResult = signOutUseCase.execute()
                if (signOutResult.isSuccess) {
                    _responseStatus.value = true
                    _toastMessage.value = "Sign out successfully"
                    Log.e("trace","SignOut")
                } else {
                    signOutResult.exceptionOrNull()?.message?.let { Log.e("trace", it) }
                    handleError(Exception(signOutResult.exceptionOrNull()?.message) )


                }
            }catch (e:Exception){
                Log.e("trace","Exception")
                handleError(e)

            }
        }
    }

    fun resetResponseStatus() {
        _responseStatus.value = null
    }

    fun resetToastMessage() {
        _toastMessage.value = null
    }
    private fun handleError(exception: Throwable) {
        val errorMessage = when (exception) {
            is UnknownHostException -> "Please check your internet connection."

            else -> { val shownMessage = exception.message
                "Error: $shownMessage"
            }
        }
        _responseStatus.value = false
        _toastMessage.value = errorMessage
    }


}