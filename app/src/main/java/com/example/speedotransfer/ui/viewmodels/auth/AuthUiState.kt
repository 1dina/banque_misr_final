package com.example.speedotransfer.ui.viewmodels.auth

import com.example.speedotransfer.data.source.remote.models.AuthData

sealed class AuthUiState {
    data object Idle : AuthUiState()
    data object Loading : AuthUiState()
    data class RegistrationSuccess(val message: String) : AuthUiState()
    data class LoginSuccess(val authData: AuthData) : AuthUiState()
    data class SignOutSuccess(val message: String) : AuthUiState()
    data class Error(val errorMessage: String) : AuthUiState()
}
